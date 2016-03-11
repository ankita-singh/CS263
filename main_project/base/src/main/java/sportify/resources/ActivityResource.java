package sportify.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import java.util.logging.Level;

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
import sportify.model.Event;
import sportify.model.Activity;

import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import com.google.appengine.api.users.*;

@Path("/activity")
public class ActivityResource {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	UserService userService = UserServiceFactory.getUserService();
	MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();


/*	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Activity> getActivity()
		{
		
		
			ArrayList<Activity> activities = new ArrayList<Activity>();
			Activity activity;
			Query q = new Query("ACTIVITY");
			PreparedQuery pq = datastore.prepare(q);
			for (Entity activityEntity : pq.asIterable()) {
				activity = new Activity();
			
				activity.setName((String)activityEntity.getProperty("name"));
				activity.setDescription((String)activityEntity.getProperty("description"));				
				
				activities.add(activity);
			
		}
		return activities;	
	}*/


	@GET
	@Path("/byOwner")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Activity> getActivitybyOwner()
		{
		
			ArrayList<Activity> activities = new ArrayList<Activity>();
			if(userService.getCurrentUser()!=null)

			{
				Activity activity;
				Filter byOwner = new FilterPredicate("ownerId", FilterOperator.EQUAL, userService.getCurrentUser().getUserId());
        		Query q = new Query("ACTIVITY").setFilter(byOwner);
				PreparedQuery pq = datastore.prepare(q);
				for (Entity activityEntity : pq.asIterable()) {
					activity = new Activity();
						
				activity.setName((String)activityEntity.getProperty("name"));
				activity.setOwnerId((String)activityEntity.getProperty("ownerId"));
				activity.setDescription((String)activityEntity.getProperty("description"));
				activities.add(activity);
			
				}
				
			}
			return activities;	
	}
	



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Activity> getAllactivities(){
		Query q = new Query("ACTIVITY");
		PreparedQuery pq = datastore.prepare(q);
		ArrayList<Activity> activities;
		String CACHE_KEY = "ALL_ACTIVITIES";
		long before = new Date().getTime();
		activities =  (ArrayList<Activity>) syncCache.get(CACHE_KEY);
		if(activities == null){
			activities = new ArrayList<Activity>();
			for (Entity activityEntity : pq.asIterable()) {
				Activity activity = new Activity();
				activity.setName((String)activityEntity.getProperty("name"));
				activity.setDescription((String)activityEntity.getProperty("description"));
				activity.setOwnerId((String)activityEntity.getProperty("ownerId"));
				activities.add(activity);
			}
			syncCache.put(CACHE_KEY, activities);
		}
		
		long after = new Date().getTime();
		System.out.println("Time taken to get all activities "+ (after - before));
		return activities;
	}





	@POST
	@Consumes("application/json")
	public void createActivity(Activity activity) {
		

		Entity activityEntity = new Entity("ACTIVITY");
		String CACHE_KEY = "ALL_ACTIVITIES";
		activityEntity.setProperty("name", activity.getName());
		activityEntity.setProperty("description", activity.getDescription());
		activityEntity.setProperty("ownerId", userService.getCurrentUser().getUserId());
		datastore.put(activityEntity);
		//syncCache.put(CACHE_KEY, activityEntity);
		
	}


}
