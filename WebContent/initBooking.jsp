<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, kurs.carrental.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Init booking</title>
</head>
<body>
<% 
	List<NameBean> offices = (List<NameBean>)request.getAttribute("offices"); 
	List<NameBean> categories = (List<NameBean>)request.getAttribute("categories"); 
	List<NameBean> statuses = (List<NameBean>)request.getAttribute("statuses"); 
%>
<form action="PageController?cmd=addBooking" method="post">
		<table>
			<tr>
				<td>Driver name:</td>
				<td><input type="text" name="dname"></td>
			</tr>
			<tr>
				<td>Driver email:</td>
				<td><input type="text" name="demail"></td>
			</tr>
			<tr>
				<td>Driver phone:</td>
				<td><input type="text" name="dphone"></td>
			</tr>
			<tr>
				<td>Driver age:</td>
				<td><input type="text" name="dage"></td>
			</tr>
			<tr>
				<td>Vehicle category:</td>
				<td>
				<select name="vehiclecategory">
				<% for (NameBean b : categories) { %>
				    <option value="<%=b.getPk() %>"><%=b.getName() %></option>
				    <% } %>
				</select>
				</td>
			</tr>
			<tr>
				<td>Pickup office:</td>
				<td>
				<select name="pickupoffice">
				<% for (NameBean b : offices) { %>
				    <option value="<%=b.getPk() %>"><%=b.getName() %></option>
				    <% } %>
				</select>
				</td>
			</tr>
			<tr>
				<td>Dropoff office:</td>
				<td>
				<select name="dropoffoffice">
				<% for (NameBean b : offices) { %>
				    <option value="<%=b.getPk() %>"><%=b.getName() %></option>
				    <% } %>
				</select>
				</td>
			</tr>
			<tr>
				<td>Booking status:</td>
				<td><select name="bookingstatus">
						<%
							for (NameBean b : statuses) {
						%>
						<option value="<%=b.getPk()%>"><%=b.getName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Add booking"></td>
			</tr>
		</table>
	</form>
</body>
</html>