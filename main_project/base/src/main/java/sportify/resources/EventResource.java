package sportify.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import sportify.dao.EventDao;
//import sportify.dao.EventInstanceDao;
import sportify.model.Event;
import sportify.model.EventInstance;

@Path("/event/{activity_id}")
public class EventResource {
	private EventDao eventDao;
	private String eventId;
	private Queue queue;
	
	public EventResource(){
		eventDao = new EventDao();
	}
	
	
	@POST
	@Consumes("application/json")
	public void createEvent(
			@PathParam("activity_id") String activityId,
			Event event) {
		
		eventDao.addEvent(event);
	}
	
	
	
}
