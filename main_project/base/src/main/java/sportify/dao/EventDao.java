package sportify.dao;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import sportify.model.Event;
import sportify.model.EventInstance;


public class EventDao {
	
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();;
	public static String EVENT_KIND = "EVENT";
	ActivityDao activityDao;
	
	public EventDao(){
		activityDao = new ActivityDao();
	}
	
	
	public void addEvent(Event event){
		Entity eventEntity = new Entity(EVENT_KIND, KeyFactory.createKey(ActivityDao.ACTIVITY_KIND, event.getActivityId()));
		eventEntity.setProperty("s_hour", event.getS_hour());
		eventEntity.setProperty("s_min", event.getS_min());
		eventEntity.setProperty("e_hour", event.getE_hour());
		eventEntity.setProperty("e_min", event.getE_min());
		eventEntity.setProperty("day", event.getDay());
		eventEntity.setProperty("owner", event.getOwner());
		datastore.put(eventEntity);
	}
	
	
	
}
