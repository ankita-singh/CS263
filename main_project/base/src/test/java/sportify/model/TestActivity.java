package sportify.model;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.google.appengine.api.datastore.KeyFactory;

public class TestActivity {

    	
    	ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
    	WebTarget service = client.target(getBaseURI());



        //CREATE AN ACTIVITY
        @Test
        public void createActivityTest() throws Exception {
        
            Activity activity = new Activity();
            activity.setName("running");
            activity.setDescription("Let's go for a run!");

            boolean passed = true;
                  
            Response response = service.path("rest").path("activity").request(MediaType.APPLICATION_JSON).post(Entity.entity(activity,MediaType.APPLICATION_JSON),Response.class);
            int status = response.getStatus();
            if (status != 204) 
            {

                System.out.println("------------------------------Test 1 Failed: Expected status 204, got: "+status+"--------------------------------");
            } else 
            {
                System.out.println("*******************Test 1 Passed*******************");
                
            }
        
        }


        //GET ALL ACTIVITIES
        @Test
        public void getAllActivitiesTest() throws Exception {
        
            String s = "";
            Activity activity = new Activity();
            boolean passed = true;
            activity.setName("running");
            activity.setDescription("Let's go for a run!");
            

            Response response = service.path("rest").path("activity").request(MediaType.APPLICATION_JSON).post(Entity.entity(activity,MediaType.APPLICATION_JSON),Response.class);
            
            try {
                
                s = service.path("rest").path("activity").request().accept(MediaType.APPLICATION_JSON).get(String.class);

            } catch (Exception e ) {
                passed = false;
                System.out.println("Test 2 Failed with exception: "+ e);
            }
            
            if(passed)
            {
                System.out.println("*******************Test 2 Passed*******************");
                System.out.println(response.getStatus()); 
            }
                           
        
        }

        //GET ACTIVITIES FROM MEMCACHE
        @Test
        public void getActivitiesFromMemcacheTest() throws Exception {
        
            String s = "";
            boolean passed = true;
            Activity activity = new Activity();
            activity.setName("running");
            activity.setDescription("Let's go for a run!");
            

            Response response = service.path("rest").path("activity").path("toMemcache").request(MediaType.APPLICATION_JSON).post(Entity.entity(activity,MediaType.APPLICATION_JSON),Response.class);
            
            try {
                s = service.path("rest").path("activity").path("fromMemcache").request().accept(MediaType.APPLICATION_JSON).get(String.class);

            } catch (Exception e ) {
                passed = false;
                System.out.println("Test 3 Failed with exception: "+ e);
            }
            
            if(passed)
            {
                System.out.println("*******************Test 3 Passed*******************");
                System.out.println(response.getStatus()); 
            }
        }


        //POST AN ACTIVITY TO MEMCACHE
        @Test
        public void addActivityToCacheTest() throws Exception {
        
            Activity activity = new Activity();
            activity.setName("running");
            activity.setDescription("Let's go for a run!");
                  
            Response response = service.path("rest").path("activity").path("toMemcache").request(MediaType.APPLICATION_JSON).post(Entity.entity(activity,MediaType.APPLICATION_JSON),Response.class);
            int status = response.getStatus();
            if (status != 204) 
            {
                System.out.println("------------------------------Test 4 Failed: Expected status 204, got: "+status+"--------------------------------");
            } else 
            {
                System.out.println("*******************Test 4 Passed*******************");
                System.out.println(response.getStatus());
                
            }
        
        }

     
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://letsplay-1228.appspot.com/").build();
    }

    }
