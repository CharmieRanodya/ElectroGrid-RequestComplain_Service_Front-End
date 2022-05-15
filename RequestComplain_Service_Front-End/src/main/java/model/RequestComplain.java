package model; 
import java.sql.*; 


public class RequestComplain 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readRequestComplain() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>RequestComplainID</th>"
		 +"<th>CustomerID</th><th>Type</th>"
		 +"<th>Description</th>" 
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from RequestComplain"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String RequestComplainID = Integer.toString(rs.getInt("RequestComplainID")); 
 String CustomerID = rs.getString("CustomerID"); 
 String Type = rs.getString("Type"); 
 String Description = rs.getString("Description"); 
 
 // Add into the html table
 output += "<tr><td>" + RequestComplainID + "</td>"; 
 output += "<td>" + CustomerID + "</td>"; 
 output += "<td>" + Type + "</td>"; 
 output += "<td>" + Description + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-RequestComplainid='" + RequestComplainID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-RequestComplainid='" + RequestComplainID + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the Requests and Complaints."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertRequestComplain(String CustomerID, String Type, 
		 String Description) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into RequestComplain (`RequestComplainID`,`CustomerID`,`Type`,`Description`)"
		 + " values (?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, CustomerID); 
		 preparedStmt.setString(3, Type);  
		 preparedStmt.setString(4, Description); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newRequestComplain = readRequestComplain(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newRequestComplain + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Request or Complaint.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateRequestComplain (String RequestComplainID, String CustomerID, String Type, 
		 String Description)  
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE RequestComplain SET CustomerID=?,Type=?,Description=? WHERE RequestComplainID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, CustomerID); 
		 preparedStmt.setString(2, Type);  
		 preparedStmt.setString(3, Description); 
		 preparedStmt.setInt(4, Integer.parseInt(RequestComplainID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newRequestComplain = readRequestComplain(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newRequestComplain + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the Request or Complain.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteRequestComplain(String RequestComplainID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from RequestComplain where RequestComplainID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(RequestComplainID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newRequestComplain = readRequestComplain(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newRequestComplain + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Request or Complain.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


