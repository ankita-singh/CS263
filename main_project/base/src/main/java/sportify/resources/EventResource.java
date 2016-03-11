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

import com.google.appengine.api.users.*;


import sportify.model.Event;


@Path("/event")

public class EventResource {
	
	
	private Queue queue;

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	UserService userService = UserServiceFactory.getUserService();


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Event> getEvent()
		{
		
		
			ArrayList<Event> events = new ArrayList<Event>();
			Event event;
			Query q = new Query("EVENT");
			PreparedQuery pq = datastore.prepare(q);
			for (Entity eventEntity : pq.asIterable()) {
				event = new Event();
			
				event.setS_hour(((Long)eventEntity.getProperty("s_hour")).intValue());
				event.setS_min(((Long)eventEntity.getProperty("s_min")).intValue());				
				event.setE_hour(((Long)eventEntity.getProperty("e_hour")).intValue());
				event.setE_min(((Long)eventEntity.getProperty("e_min")).intValue());				
				event.setOwner((String)eventEntity.getProperty("owner"));
				event.setOwnerId((String)eventEntity.getProperty("ownerId"));
				event.setActivity((String)eventEntity.getProperty("activity"));
				events.add(event);
			
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
				event.setOwnerId((String)eventEntity.getProperty("ownerId"));
				event.setActivity((String)eventEntity.getProperty("activity"));
				events.add(event);
			
				}
				
			}
			return events;	
	}
	

	

	@POST
	@Consumes("application/json")
	public void createEvent(Event event) {
		

		Entity eventEntity = new Entity("EVENT");
		eventEntity.setProperty("s_hour", event.getS_hour());
		eventEntity.setProperty("s_min", event.getS_min());
		eventEntity.setProperty("e_hour", event.getE_hour());
		eventEntity.setProperty("e_min", event.getE_min());
		eventEntity.setProperty("day", event.getDay());
		eventEntity.setProperty("activity", event.getActivity());
		eventEntity.setProperty("owner", userService.getCurrentUser().getNickname());
		eventEntity.setProperty("ownerId", userService.getCurrentUser().getUserId());
		datastore.put(eventEntity);
		
	}
		/*@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createEvent(@FormParam("s_hour") int s_hour,
							@FormParam("s_min") int s_min,
							@FormParam("e_hour") int e_hour,
							@FormParam("e_min") int e_min,
							@FormParam("day") int day,
							@FormParam("activity") String activity

		) {
		

		Entity eventEntity = new Entity("EVENT");
		eventEntity.setProperty("s_hour", s_hour);
		eventEntity.setProperty("s_min", s_min);
		eventEntity.setProperty("e_hour", e_hour);
		eventEntity.setProperty("e_min", e_min);
		eventEntity.setProperty("day", day);
		eventEntity.setProperty("activity", activity);
		eventEntity.setProperty("owner", userService.getCurrentUser().getUserId());;
		datastore.put(eventEntity);
		
	}*/

	/*@PATH("{eventId}")
	@PUT
	@Consumes("application/json")
	public void createEvent( Event event) {
		
		Entity eventEntity = datastore.get(eventId);
		eventEntity.setProperty("s_hour", event.getS_hour());
		eventEntity.setProperty("s_min", event.getS_min());
		eventEntity.setProperty("e_hour", event.getE_hour());
		eventEntity.setProperty("e_min", event.getE_min());
		eventEntity.setProperty("day", event.getDay());
		eventEntity.setProperty("activity", event.getActivity());
		eventEntity.setProperty("owner", userService.getCurrentUser().getNickname());
		eventEntity.setProperty("ownerId", userService.getCurrentUser().getUserId());
		datastore.put(eventEntity);
		
	}*/
	
	
}

