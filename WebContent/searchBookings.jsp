<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, kurs.carrental.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search bookings</title>
</head>
<body>
	<%
		List<NameBean> offices = (List<NameBean>) request.getAttribute("offices");
		List<NameBean> statuses = (List<NameBean>) request.getAttribute("statuses");
	%>
	<form action="PageController?cmd=getBookings" method="post">
		<table>
			<tr>
				<td>Booking ID:</td>
				<td><input type="text" name="bookingid"></td>
			</tr>
			<tr>
				<td>Keyword:</td>
				<td><input type="text" name="keyword"></td>
			</tr>			
			<tr>
				<td>Booking status:</td>
				<td><select name="bookingstatus">
				<option value="">All statuses</option>
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
				<td><input type="submit" value="Search bookings"></td>
			</tr>
		</table>
	</form>
</body>
</html>