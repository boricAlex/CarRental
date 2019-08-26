package kurs.carrental.commands;

import java.util.Date;

import kurs.carrental.beans.Booking;
import kurs.carrental.services.BookingService;

public class AddBookingCommand extends Command {

	public AddBookingCommand(String jsp) {
		super(jsp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {	
		BookingService srv = new BookingService(dbUrl, dbUser, dbPswd);
		srv.createConnection(true);
		
		Booking bk = new Booking();
		bk.setVehicleCategoryId(Integer.parseInt(req.getParameter("vehiclecategory")));
		bk.setPickupOfficeId(Integer.parseInt(req.getParameter("pickupoffice")));
		bk.setDropoffOfficeId(Integer.parseInt(req.getParameter("dropoffoffice")));
		bk.setStatusId(1);
		bk.setDriverName(req.getParameter("dname"));
		bk.setDriverEmail(req.getParameter("demail"));
		bk.setDriverPhone(req.getParameter("dphone"));
		String dAge = req.getParameter("dage");
		int age = (dAge == null || dAge.isEmpty()) ? 0 : Integer.parseInt(dAge);
		bk.setDriverAge(age);
		bk.setPickupTime(new Date());
		bk.setDropoffTime(new Date());
		
		int rows = srv.insertBooking(bk);
		req.setAttribute("booking", bk);
		
		return nextPage;
	}

}
