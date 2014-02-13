package persistence;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MyServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
//		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
//		EntityManager em = emf.createEntityManager();
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String username = req.getParameter("username");
		out.println("<HTML><BODY>");
		
		if(username.equalsIgnoreCase("ERROREVENTS"))
			out.println(PersistenceSimpleQueries.findAllErrorEvents().size() + " ErrorEvents exist!");
//			out.println("SOME" + " ErrorEvents exist!");
		else
			out.println("Invalid option - wouldn't a dropdown menu be smarter, you idiot?");
		
		out.println("</BODY></HTML>");
		
//		em.close();
	}
}
