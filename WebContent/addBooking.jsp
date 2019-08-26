<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="kurs.carrental.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add booking</title>
<link rel="stylesheet" type = "text/css" href = "css/test.css" />
</head>
<body>
	<%
		Booking bk = (Booking) request.getAttribute("booking");
	%>
	<table>
		<tr>
			<td>Booking ID:</td>
			<td><%=bk.getBookingId()%></td>
		</tr>
		<tr>
			<td>Driver name:</td>
			<td><%=bk.getDriverName()%></td>
		</tr>
		<tr>
			<td>Driver email:</td>
			<td><%=bk.getDriverEmail()%></td>
		</tr>
		<tr>
			<td>Driver phone:</td>
			<td><%=bk.getDriverPhone()%></td>
		</tr>
		<tr>
			<td>Vehicle category:</td>
			<td><%=bk.getVehicleCategoryId()%></td>
		</tr>
		<tr>
			<td>Pickup office:</td>
			<td><%=bk.getPickupOfficeId()%></td>
		</tr>
		<tr>
			<td>Dropoff office:</td>
			<td><%=bk.getDropoffOfficeId()%></td>
		</tr>
		<tr>
			<td>Pickup time:</td>
			<td><%=bk.getPickupTime()%></td>
		</tr>
		<tr>
			<td>Dropoff time:</td>
			<td><%=bk.getDropoffTime()%></td>
		</tr>
	</table>
</body>
</html>