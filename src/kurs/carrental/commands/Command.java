package kurs.carrental.commands;

import javax.servlet.http.HttpServletRequest;

public abstract class Command {
	protected HttpServletRequest req;
	protected String nextPage;
	protected String dbUrl;
	protected String dbUser;
	protected String dbPswd;
	
	public Command(String jsp) {
		nextPage = jsp;
	}
	
	public void setDbUrl(String s) { dbUrl = s; }
	public void setDbUser(String s) { dbUser = s; }
	public void setDbPswd(String s) { dbPswd = s; }
	
	public void setRequest(HttpServletRequest r) { req = r; }
	
	public abstract String execute() throws Exception;

}
