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
import com.google.appengine.api.memcache.MemcacheSerialization;


@SuppressWarnings("serial")
public class DatastoreServlet extends HttpServlet {


  public static byte[] serializeObject(Object obj) throws IOException
  {
    ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bytesOut);
    oos.writeObject(obj);
    oos.flush();
    byte[] bytes = bytesOut.toByteArray();
    bytesOut.close();
    oos.close();
    return bytes;
  }

  public static Object deserializeBytes(byte[] bytes) throws IOException, ClassNotFoundException
{
    ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = new ObjectInputStream(bytesIn);
    Object obj = ois.readObject();
    ois.close();
    return obj;
}

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html");
      resp.getWriter().println("<html><body>");

      String name = req.getParameter("keyname");
      String value = req.getParameter("value");
      String kind = req.getParameter("kind");
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
      syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));


     if(name == null && value ==null) 
      {
       
          Query q = new Query("TaskData"); 
          PreparedQuery pq = datastore.prepare(q);  
          
            List<String> list = new ArrayList<>();
            resp.getWriter().println("Datastore Values <br><br>"); 
            for (Entity result : pq.asIterable())
            {
             Key gen_key = result.getKey();
             String entity_name = gen_key.getName(); 
             list.add(entity_name);   
             String entity_value = (String) result.getProperty("value"); 
             Date entity_date = (Date)result.getProperty("date"); 
             resp.getWriter().println(entity_name+",  "+entity_value+",  "+entity_date);
             resp.getWriter().println("<br>");
             
            }
            
            resp.getWriter().println("<br><br><br>"); 
            resp.getWriter().println("Memcache Values <br><br>");
            String cache_value;
            
          for (String key_value : list)
            {
             
             Key item_key1 = KeyFactory.createKey("TaskData",key_value);
             cache_value =  (String) syncCache.get(key_value);
             resp.getWriter().println(key_value+" : ");
             resp.getWriter().println(cache_value+"<br><br>");
             //String cache_entity_value = (String) cache_value.getProperty("value"); 
             //Date cache_entity_date = (Date)cache_value.getProperty("date"); 
             //resp.getWriter().println(key_value+",  "+cache_entity_value+",  "+cache_entity_date);
             
             
             

             



                    














            }
         

      } 



      else if (name != null && value != null ) {

      byte[] mem_object;
      Entity tne = new Entity("TaskData",name);
      Key item_key1 = KeyFactory.createKey("TaskData",name);
      tne.setProperty("value",value);
      Date dNow = new Date( );
      tne.setProperty("date", dNow);
      datastore.put(tne);
      //resp.getWriter().println("Stored "+name+ " and "+value+" in Datastore");
      //mem_object = serializeObject(tne);
      //Key key1 = KeyFactory.createKey(tne, name);
      syncCache.put(name,value);
      resp.getWriter().println("Stored "+name+ " and "+value+" in Memcache");
      resp.getWriter().println("<br>"+name);
      //resp.getWriter().println("<br>"+mem_object);



      } 

      else if (name != null ) 
      {       

              String mem_value;
              Entity item_entity;
              boolean mem_pres = false;
              boolean data_pres = false;
              
              Key item_key = KeyFactory.createKey("TaskData",name);
              try
              {
                  mem_value =  (String)syncCache.get(name);

                  if(mem_value == null)
                  {

                      
                      item_entity = datastore.get(item_key);
                      Key gen_key = item_entity.getKey();
                      String entity_name = gen_key.getName();
                      mem_value = (String)item_entity.getProperty("value");
                      //mem_value = serializeObject(item_entity);
                      resp.getWriter().println("Value stored in cache is"+mem_value);
                      syncCache.put(name, mem_value);
                      resp.getWriter().println("<br>"+item_key);
                      resp.getWriter().println("<br>"+mem_value);
                      data_pres = true;
                      resp.getWriter().println(name+":(Datastore)");

                  }  

                  else
                  {

                    item_entity = datastore.get(item_key);
                    if(item_entity!= null)
                    resp.getWriter().println(name+": (Both)");
                      
                    else

                    resp.getWriter().println(name+" : (MemCache)");
                    
                    
                  }     
                  
              }
              catch (EntityNotFoundException e)
              {

                 resp.getWriter().println(name+" : (neither)"+"<br>");
                 resp.getWriter().println("Entity doesn't exists");

              } 

      }


      else 

          resp.getWriter().println("Invalid Request");


      resp.getWriter().println("</body></html>");
  }
}
