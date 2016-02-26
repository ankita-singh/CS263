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

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import sportify.model.Event;
import sportify.model.Activity;

@Path("/activity")
public class ActivityResource {
	
	/*private ActivityDao activityDao;
	private EventDao eventDao;
	
	
	public ActivityResource(){
		activityDao = new ActivityDao();
		eventDao = new EventDao();
		
	}
	
	
	@POST
	@Consumes("application/json")
	public void createActivity(Activity activity){
		activityDao.addActivity(activity);
	}
	*/

}
