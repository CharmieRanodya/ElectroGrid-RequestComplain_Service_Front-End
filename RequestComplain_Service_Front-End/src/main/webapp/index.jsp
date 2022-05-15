<%@page import="model.RequestComplain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("CustomerID") != null) 
{ 
 RequestComplain RequestComplainObj = new RequestComplain(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidRequestComplainIDSave") == "") 
 { 
 stsMsg = RequestComplainObj.insertRequestComplain(request.getParameter("CustomerID"), 
 request.getParameter("Type"),  
 request.getParameter("Description")); 
 } 
else//Update----------------------
 { 
 stsMsg = RequestComplainObj.updateRequestComplain(request.getParameter("hidRequestComplainIDSave"), 
 request.getParameter("CustomerID"), 
 request.getParameter("Type"), 
 request.getParameter("Description")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null) 
{ 
 RequestComplain RequestComplainObj = new RequestComplain(); 
 String stsMsg = 
 RequestComplainObj.deleteRequestComplain(request.getParameter("hidRequestComplainIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RequestComplain Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>RequestComplain Management V10.1</h1>
<form id="formRequestComplain" name="formRequestComplain">
 CustomerID: 
 <input id="CustomerID" name="CustomerID" type="text" 
 class="form-control form-control-sm">
 <br> Type: 
 <input id="Type" name="Type" type="text" 
 class="form-control form-control-sm">
 <br> Description: 
 <input id="Description" name="Description" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidRequestComplainIDSave" 
 name="hidRequestComplainIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divRequestComplainGrid">
 <%
 RequestComplain RequestComplainObj = new RequestComplain(); 
 out.print(RequestComplainObj.readRequestComplain()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
