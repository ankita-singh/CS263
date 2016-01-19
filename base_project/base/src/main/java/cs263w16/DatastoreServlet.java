package cs263w16;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.*;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;

@SuppressWarnings("serial")
public class DatastoreServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html");
      resp.getWriter().println("<html><body>");

      String name = req.getParameter("keyname");
      String value = req.getParameter("value");
      String kind = req.getParameter("kind");
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


  


     if(name == null && value ==null) 
      {

        
        
          Query q = new Query("TaskData"); 
          PreparedQuery pq = datastore.prepare(q);  
          if(q==null)
            resp.getWriter().println("Empty"); 
          else
          {

            for (Entity result : pq.asIterable())
            {
             Key gen_key = result.getKey();
             String entity_name = gen_key.getName();    
             String entity_value = (String) result.getProperty("value"); 
             Date entity_date = (Date)result.getProperty("date"); 

             resp.getWriter().println(entity_name+",  "+entity_value+",  "+entity_date);
             resp.getWriter().println("<br>"); 
            }
          }
         

      } 



      else if (name != null && value != null ) {

      Entity tne = new Entity("TaskData",name);
      tne.setProperty("value",value);
      Date dNow = new Date( );
	    tne.setProperty("date", dNow);
      datastore.put(tne);
      resp.getWriter().println("Stored "+name+ " and "+value+" in Datastore");


  		}	

  		else if (name != null )	
  		{
  			Entity item_entity;
  			Key item_key = KeyFactory.createKey("TaskData",name);
  			try{

  				item_entity = datastore.get(item_key);
  				String name1 = (String)item_entity.getProperty("value");
  				Date date1 = (Date)item_entity.getProperty("date");
          
          Key gen_key = item_entity.getKey();
        String entity_name = gen_key.getName();    
       
  				resp.getWriter().println("Name:  "+entity_name+"<br>value:  "+name1+"<br>date:  "+date1);
  			}
  			catch (EntityNotFoundException e) {
    			
    			resp.getWriter().println("Entity doesn't exists");

			}	

  		}


  		else 

  				resp.getWriter().println("Invalid Request");


      resp.getWriter().println("</body></html>");
  }
}