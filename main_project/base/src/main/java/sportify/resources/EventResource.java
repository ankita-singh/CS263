package sportify.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import com.google.appengine.api.users.*;


import sportify.model.Event;


@Path("/event")

public class EventResource {
	
	
	private Queue queue;

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	UserService userService = UserServiceFactory.getUserService();
	MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Event> getEvent()
		{
		
		
			ArrayList<Event> events = new ArrayList<Event>();
			Event event;
			Query q = new Query("EVENT");
			PreparedQuery pq = datastore.prepare(q);
			
			String CACHE_KEY = "ALL_EVENTS";
			events =  (ArrayList<Event>) syncCache.get(CACHE_KEY);
			if(events == null){
				
				events = new ArrayList<Event>();
				for (Entity eventEntity : pq.asIterable()) {
				event = new Event();
			
				event.setS_hour(((Long)eventEntity.getProperty("s_hour")).intValue());
				event.setS_min(((Long)eventEntity.getProperty("s_min")).intValue());				
				event.setE_hour(((Long)eventEntity.getProperty("e_hour")).intValue());
				event.setE_min(((Long)eventEntity.getProperty("e_min")).intValue());				
				event.setOwner((String)eventEntity.getProperty("owner"));
				event.setOwnerId((String)eventEntity.getProperty("ownerId"));
				event.setDay((String)eventEntity.getProperty("day"));
				event.setId(KeyFactory.keyToString(eventEntity.getKey()));
				event.setActivity((String)eventEntity.getProperty("activity"));
				events.add(event);
			}

			syncCache.put(CACHE_KEY, events, Expiration.byDeltaSeconds(1));
			
			
		}
		return events;	
	}


	@GET
	@Path("/byOwner")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Event> getEventbyOwner()
		{
		
			ArrayList<Event> events = new ArrayList<Event>();
			if(userService.getCurrentUser()!=null)

			{
				Event event;
				Filter byOwner = new FilterPredicate("ownerId", FilterOperator.EQUAL, userService.getCurrentUser().getUserId());
        		Query q = new Query("EVENT").setFilter(byOwner);
				PreparedQuery pq = datastore.prepare(q);
				for (Entity eventEntity : pq.asIterable()) {
					event = new Event();
			
				event.setS_hour(((Long)eventEntity.getProperty("s_hour")).intValue());
				event.setS_min(((Long)eventEntity.getProperty("s_min")).intValue());				
				event.setE_hour(((Long)eventEntity.getProperty("e_hour")).intValue());
				event.setE_min(((Long)eventEntity.getProperty("e_min")).intValue());				
				event.setOwner((String)eventEntity.getProperty("owner"));
				event.setDay((String)eventEntity.getProperty("day"));
				event.setOwnerId((String)eventEntity.getProperty("ownerId"));
				event.setActivity((String)eventEntity.getProperty("activity"));
				event.setId(KeyFactory.keyToString(eventEntity.getKey()));
				events.add(event);
			
				}
				
			}
			return events;	
	}
	

	

	@GET
	@Path("/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Event getEventbyId(@PathParam("Id") String id)
		{
		 
		Event event = new Event();

			if(userService.getCurrentUser()!=null)

			{
				Key event_key = KeyFactory.stringToKey(id);
				Entity event_entity;
				try {
					event_entity = datastore.get(event_key);
					event.setDay((String) event_entity.getProperty("day"));
		            event.setS_hour((int) event_entity.getProperty("s_hour"));
		            event.setS_min((int) event_entity.getProperty("s_min"));
		            event.setE_hour((int) event_entity.getProperty("e_hour"));
		            event.setE_min((int) event_entity.getProperty("e_min"));
		            event.setActivity((String) event_entity.getProperty("activity"));
		            event.setOwner((String) event_entity.getProperty("owner"));
		            event.setOwnerId((String) event_entity.getProperty("ownerId"));
					
				} catch (EntityNotFoundException e) {
					
					e.printStackTrace();
				}	
				
			}
			return event;	
	}



	@POST
	@Consumes("application/json")
	public Event createEvent(Event event) {
		

		Entity eventEntity = new Entity("EVENT");
		eventEntity.setProperty("s_hour", event.getS_hour());
		eventEntity.setProperty("s_min", event.getS_min());
		eventEntity.setProperty("e_hour", event.getE_hour());
		eventEntity.setProperty("e_min", event.getE_min());
		eventEntity.setProperty("day", event.getDay());
		eventEntity.setProperty("activity", event.getActivity());
		if(userService.getCurrentUser()!= null)
		{
		eventEntity.setProperty("owner", userService.getCurrentUser().getNickname());
		eventEntity.setProperty("ownerId", userService.getCurrentUser().getUserId());
	    }

	    //for integration testing
	    else
	    {
	    	eventEntity.setProperty("owner", "test_user");
			eventEntity.setProperty("ownerId", "test_id");
	    }
		datastore.put(eventEntity);

		String id = KeyFactory.keyToString(eventEntity.getKey());
		event.setId(id);

		String CACHE_KEY = "ALL_EVENTS";
		ArrayList<Event> events;
		events =  (ArrayList<Event>) syncCache.get(CACHE_KEY);
		if(events == null){
			
			events = new ArrayList<Event>();
		}
		events.add(event);
		syncCache.put(CACHE_KEY, events, Expiration.byDeltaSeconds(1));

		return event;
		
	}


	@DELETE
  	@Path("/{id}")
  	public void deleteEvent(@PathParam("id") String id) {

  	Queue queue = QueueFactory.getDefaultQueue();
  	queue.add(TaskOptions.Builder.withUrl("/rest/event/taskQueue/"+id).method(TaskOptions.Method.DELETE));
	//datastore.delete(KeyFactory.stringToKey(id));
  }

	@DELETE
  	@Path("/taskQueue/{id}")
  	public void deleteEventQueue(@PathParam("id") String id) {

	datastore.delete(KeyFactory.stringToKey(id));
  }


		
	
}

