<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, kurs.carrental.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search bookings results</title>
<link  rel="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/test.css" />
</head>
<body>
	<%
		Integer PageCount = (Integer) request.getAttribute("NumberofPages");	
		String pagestart = request.getParameter("pageNum");
		Integer start = 1;
		if(pagestart !=null){
			start = Integer.parseInt(pagestart);
		}
		for(int i=1; i <= PageCount; i++){
			%><a href="PageController?cmd=getBookings&pageNum=<%=i%>"><%=i%></a>
			<%
		}
		
		List<Booking> bks = (List<Booking>) request.getAttribute("bookings");
	%>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Age</th>
			<th>Status</th>
			<th>Pickup</th>
			<th>Dropoff</th>
			<th>Pickup time</th>
			<th>Dropoff time</th>
		</tr>
		<%
			for (Booking bk : bks) {
		%>
		<tr>
			<td><%=bk.getBookingId()%></td>
			<td><%=bk.getDriverName()%></td>
			<td><%=bk.getDriverEmail()%></td>
			<td><%=bk.getDriverPhone()%></td>
			<td><%=bk.getDriverAge()%></td>
			<td><%=bk.getStatusId()%></td>
			<td><%=bk.getPickupOfficeId()%></td>
			<td><%=bk.getDropoffOfficeId()%></td>
			<td><%=bk.getPickupTime()%></td>
			<td><%=bk.getDropoffTime()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>