// The Worker servlet should be mapped to the "/worker" URL.

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


public class Worker extends HttpServlet {

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


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key_name = request.getParameter("keyname");
        String key_value = request.getParameter("value");
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
       

        byte[] mem_object;
      	Entity tne = new Entity("TaskData",key_name);
      	Key item_key = KeyFactory.createKey("TaskData",key_name);
      	tne.setProperty("value",key_value);
      	Date dNow = new Date( );
      	tne.setProperty("date", dNow);
      	datastore.put(tne);
        mem_object = serializeObject(tne);
        syncCache.put(item_key,mem_object);
     

    }
}