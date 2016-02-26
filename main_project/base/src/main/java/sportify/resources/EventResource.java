package sportify.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
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
	
	
	
	
	@POST
	//@Consumes("application/json")
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
		
	}
	
	
	
}
