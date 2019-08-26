package kurs.carrental.commands;

import java.util.List;

import kurs.carrental.beans.NameBean;
import kurs.carrental.services.BookingService;

public class InitBookingCommand extends Command {

	public InitBookingCommand(String jsp) {
		super(jsp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String execute() throws Exception {
		BookingService srv = new BookingService(dbUrl, dbUser, dbPswd);
		srv.createConnection(true);
		List<NameBean> offices = srv.getNamedItems("Office", "OfficeId", "Name");
		List<NameBean> categories = srv.getNamedItems("VehicleCategory", "CategoryId", "Name");
		List<NameBean> statuses = srv.getNamedItems("BookingStatus", "StatusId", "Status");
		req.setAttribute("statuses", statuses);
		req.setAttribute("offices", offices);
		req.setAttribute("categories", categories);
		
		return nextPage;
	}

}
