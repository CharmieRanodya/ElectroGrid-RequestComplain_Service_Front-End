package com; 
import model.RequestComplain; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/RequestComplain") 
public class RequestComplainService 
{ 
 RequestComplain RequestComplainObj = new RequestComplain(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readRequestComplain() 
  { 
  return RequestComplainObj.readRequestComplain(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertRequestComplain(@FormParam("CustomerID") String CustomerID, 
  @FormParam("Type") String Type,  
  @FormParam("Description") String Description) 
 { 
  String output = RequestComplainObj.insertRequestComplain(CustomerID, Type, Description); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateRequestComplain(String RequestComplainData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject RequestComplainObject = new JsonParser().parse(RequestComplainData).getAsJsonObject(); 
 //Read the values from the JSON object
  String RequestComplainID = RequestComplainObject.get("RequestComplainID").getAsString(); 
  String CustomerID = RequestComplainObject.get("CustomerID").getAsString(); 
  String Type = RequestComplainObject.get("Type").getAsString(); 
  String Description = RequestComplainObject.get("Description").getAsString(); 
  String output = RequestComplainObj.updateRequestComplain(RequestComplainID, CustomerID, Type, Description); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteItem(String RequestComplainData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(RequestComplainData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String RequestComplainID = doc.select("RequestComplainID").text(); 
  String output = RequestComplainObj.deleteRequestComplain(RequestComplainID); 
 return output; 
 }
}
