package sportify.dao;

import sportify.model.Activity;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.Expiration;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ActivityDao {
	
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private MemcacheService syncCache;
	private Expiration fiveMinutes;
	public static String ACTIVITY_KIND = "ACTIVITY";
	
	public ActivityDao(){
		syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		
	}
	
	
	public void addActivity(Activity activity){
		
		Entity activityEntity = new Entity(ACTIVITY_KIND, activity.getAct_id());
		activityEntity.setProperty("name", activity.getName());
		activityEntity.setProperty("description", activity.getDescription());
		datastore.put(activityEntity);

	}
	
	
}
