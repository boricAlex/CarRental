package kurs.carrental.commands;

import java.util.List;

import kurs.carrental.beans.Booking;
import kurs.carrental.services.BookingService;

public class GetBookingsCommand extends Command {

	public GetBookingsCommand(String jsp) {
		super(jsp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		BookingService srv = new BookingService(dbUrl, dbUser, dbPswd);
		srv.createConnection(true);
		
		String pagestart = req.getParameter("pageNum");
		Integer numPage = 1;
		if(pagestart !=null) {
			numPage = Integer.parseInt(pagestart);
		}
		
		String bkId = req.getParameter("bookingid");
		Integer bookingId = (bkId == null || bkId.isEmpty()) ? null : Integer.parseInt(bkId);
		
		String bkStatus = req.getParameter("bookingstatus");
		Integer bookingStatusId = (bkStatus == null || bkStatus.isEmpty()) ? null : Integer.parseInt(bkStatus);
		
		String keyword = req.getParameter("keyword");
		
		String psize = req.getServletContext().getInitParameter("PageSize");
		Integer PageSize = Integer.parseInt(psize);
		
		Integer count = srv.countSearchBookings(bookingId, keyword, bookingStatusId, null, null);
		
		Integer PageCount = count/PageSize;
		if(count % PageSize > 0) PageCount++;
		
		Integer start = (numPage-1)*PageSize;
		
		List<Booking> bks = srv.searchBookings(bookingId, keyword, bookingStatusId, null, null, start, PageSize);
		req.setAttribute("bookings", bks);
		req.setAttribute("NumberofPages", PageCount);
		req.setAttribute("pageNum", start);
		return nextPage;
	}

}