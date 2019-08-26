package kurs.carrental.commands;

import java.util.List;

import kurs.carrental.beans.NameBean;
import kurs.carrental.services.BookingService;

public class SearchBookingsCommand extends Command {

	public SearchBookingsCommand(String jsp) {
		super(jsp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		BookingService srv = new BookingService(dbUrl, dbUser, dbPswd);
		srv.createConnection(true);
		List<NameBean> offices = srv.getNamedItems("Office", "OfficeId", "Name");
		List<NameBean> statuses = srv.getNamedItems("BookingStatus", "StatusId", "Status");
		req.setAttribute("offices", offices);
		req.setAttribute("statuses", statuses);
		
		return nextPage;
	}

}
