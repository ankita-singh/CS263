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
 
public class TestEvent {

    	
    	ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
    	WebTarget service = client.target(getBaseURI());



        //CREATE AN EVENT
        @Test
        public void createEventTest() throws Exception {
        
            Event event1 = new Event();
            event1.setDay(8);
            event1.setS_hour(8);
            event1.setS_min(30);
            event1.setE_hour(9);
            event1.setE_min(30);
            event1.setActivity("football");
            event1.setOwner("ankita");
            event1.setOwnerId("123");
            Response response = service.path("rest").path("event").request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);
            int status = response.getStatus();
            if (status != 200) 
            {
                System.out.println("------------------------------Test 1 Failed: Expected status 200, got: "+status+"--------------------------------");
            } else 
            {
                System.out.println("*******************Test 1 Passed*******************");
                
            }
        
        }


        //GET EVENT BY ID
        @Test
        public void getEventbyIdTest() throws Exception {
        
            //create an event which would be fetched later
            Event event1 = new Event();
            String s;
            event1.setDay(1);
            event1.setS_hour(8);
            event1.setS_min(30);
            event1.setE_hour(9);
            event1.setE_min(30);
            event1.setActivity("football");
            event1.setOwner("ankita");
            event1.setOwnerId("123");

            Response response = service.path("rest").path("event").request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);
            
            Event event2 = response.readEntity(Event.class);
            String event_key = event2.getId();

            //Response response = service.path("rest").path("event").path(event_key).request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);

            
            try {
                s = service.path("rest").path("event").path(event_key).request().accept(MediaType.APPLICATION_JSON).get(String.class);

            } catch (Exception e ) {
                System.out.println("Test 2 Failed with exception: "+ e);
            }
            
                System.out.println("*******************Test 2 Passed*******************");
                
              
        
        }


        //GET ALL EVENTS
        @Test
        public void getEventTest() throws Exception {
        
            String s = "";
            Event event1 = new Event();
            event1.setDay(7);
            event1.setS_hour(8);
            event1.setS_min(30);
            event1.setE_hour(9);
            event1.setE_min(30);
            event1.setActivity("football");
            event1.setOwner("ankita");
            event1.setOwnerId("123");

            Response response = service.path("rest").path("event").request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);
            Event event2 = response.readEntity(Event.class);
            String event_key = event2.getId();
            try {
                s = service.path("rest").path("event").request().accept(MediaType.APPLICATION_JSON).get(String.class);

            } catch (Exception e ) {
                System.out.println("Test 3 Failed with exception: "+ e);
            }
            
                System.out.println("*******************Test 3 Passed*******************");
                
                
              
        
        }


        //DELETE AN EVENT (using taskqueue) 
        @Test
        public void deleteEventTest() throws Exception {
        
            //create an event (which would be deleted)
            boolean passed = false;
            Event event1 = new Event();
            String s;
            event1.setDay(1);
            event1.setS_hour(8);
            event1.setS_min(30);
            event1.setE_hour(9);
            event1.setE_min(30);
            event1.setActivity("football");
            event1.setOwner("ankita");
            event1.setOwnerId("123");

            Response response = service.path("rest").path("event").request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);
            
            Event event2 = response.readEntity(Event.class);
            String event_key = event2.getId();

            service.path("rest").path("event").path("taskQueue").path(event_key).request().delete();

            
            try {
                s = service.path("rest").path("event").path("byId").path(event_key).request().accept(MediaType.APPLICATION_JSON).get(String.class);

            } catch (Exception e ) {

                passed = true;
                System.out.println("*******************Test 4 Passed*******************");
            }
            
                if(!passed)
                System.out.println("Test 4 Failed: "+ event_key + " found in datastore following delete.  DB dump:");
                
              
        
        }


        //GET EVENT BY OWNER
        @Test
        public void getEventbyOwnerTest() throws Exception {
        
            //create an event which would be fetched later
            Event event1 = new Event();
            String s;
            event1.setDay(1);
            event1.setS_hour(8);
            event1.setS_min(30);
            event1.setE_hour(9);
            event1.setE_min(30);
            event1.setActivity("football");
            event1.setOwner("ankita");
            event1.setOwnerId("123");

            Response response = service.path("rest").path("event").request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);
            
            Event event2 = response.readEntity(Event.class);
            String event_key = event2.getOwnerId();

            //Response response = service.path("rest").path("event").path(event_key).request(MediaType.APPLICATION_JSON).post(Entity.entity(event1,MediaType.APPLICATION_JSON),Response.class);

            
            try {
                s = service.path("rest").path("event").path("byOwner").request().accept(MediaType.APPLICATION_JSON).get(String.class);

            } catch (Exception e ) {
                System.out.println("Test 5 Failed with exception: "+ e);
            }
            
                System.out.println("*******************Test 5 Passed*******************");
                
                
              
        
        }
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://letsplay-1228.appspot.com/").build();
    }

    }
