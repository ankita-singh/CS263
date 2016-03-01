package sportify.resources;

import java.util.ArrayList;
import java.util.Date;
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
import sportify.model.Event;
import sportify.model.Activity;

import com.google.appengine.api.users.*;

@Path("/activity")
public class ActivityResource {
	
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	UserService userService = UserServiceFactory.getUserService();


	@GET
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
	}


	@POST
	@Consumes("application/json")
	public void createActivity(Activity activity) {
		

		Entity activityEntity = new Entity("ACTIVITY");
		activityEntity.setProperty("name", activity.getName());
		activityEntity.setProperty("description", activity.getDescription());
		datastore.put(activityEntity);
		
	}


}
