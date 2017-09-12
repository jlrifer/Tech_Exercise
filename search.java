

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String             url              = "jdbc:mysql://ec2jlrifer.ddns.net:3306/myDB";
	static String             user             = "newremoteuser";
	static String             password         = "password";
	static Connection         connection       = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<head><title>Assignment Schedule</title></head>");
		response.getWriter().println("<html><body><h1 style=\"text-align:center;\">Assignment Schedule</h1>");
		response.getWriter().println("<div style=\"width:25%; position:fixed; background-color:lightGray; padding:5px; padding-right:15px; display:inline-block;\"> ");
		response.getWriter().println("<h3 style=\"text-align:center;\">Menu</h3>");
		response.getWriter().println("<ul><li> <a href=\"http://ec2jlrifer.ddns.net:8080/TechExercise/MyServlet\" style=\"text-decoration:none; color:black;\">Show All Assignments</a></li>");
		response.getWriter().println("<li> <a href=\"http://ec2jlrifer.ddns.net:8080/TechExercise/add\" style=\"text-decoration:none; color:black;\">Add Assignment</a></li>");
		response.getWriter().println("<li> <a href=\"http://ec2jlrifer.ddns.net:8080/TechExercise/search\" style=\"text-decoration:none; color:black;\">Search Assignments</a></li></ul></div>");
		response.getWriter().println("<form action=\"http://ec2jlrifer.ddns.net:8080/TechExercise/search\" method=\"post\">");
		response.getWriter().println("<div style=\"display:inline-block; margin-left:35%;\">");
		response.getWriter().println("<input type=\"text\" name=\"search\" placeholder=\"Search...\">");
		response.getWriter().println("<input type=\"submit\" value=\"Search\"></div></form></body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<head><title>Assignment Schedule</title></head>");
		response.getWriter().println("<html><body><h1 style=\"text-align:center;\">Assignment Schedule</h1>");
		response.getWriter().println("<div style=\"width:25%; position:fixed; background-color:lightGray; padding:5px; padding-right:15px; display:inline-block;\"> ");
		response.getWriter().println("<h3 style=\"text-align:center;\">Menu</h3>");
		response.getWriter().println("<ul><li> <a href=\"http://ec2jlrifer.ddns.net:8080/TechExercise/MyServlet\" style=\"text-decoration:none; color:black;\">Show All Assignments</a></li>");
		response.getWriter().println("<li> <a href=\"http://ec2jlrifer.ddns.net:8080/TechExercise/add\" style=\"text-decoration:none; color:black;\">Add Assignment</a></li>");
		response.getWriter().println("<li> <a href=\"http://ec2jlrifer.ddns.net:8080/TechExercise/search\" style=\"text-decoration:none; color:black;\">Search Assignments</a></li></ul></div>");
		response.getWriter().println("<form action=\"http://ec2jlrifer.ddns.net:8080/TechExercise/search\" method=\"post\">");
		response.getWriter().println("<div style=\"display:inline-block; margin-left:35%;\">");
		response.getWriter().println("<input type=\"text\" name=\"search\" placeholder=\"Search...\">");
		response.getWriter().println("<input type=\"submit\" value=\"Search\">");
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    System.out.println("Where is your MySQL JDBC Driver?");
		    e.printStackTrace();
		    return;
		}
		connection = null;
		  
		try {
		   connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
		   System.out.println("Connection Failed! Check output console");
		   e.printStackTrace();
		   return;
		}
		if (connection == null) {
		   System.out.println("Failed to make connection!");
		}
		
		try {
			String search = request.getParameter("search");
			String selectSQL = "select className, assignmentType, dueDate, details from assignment where (className like '%" + search + "%') || (assignmentType like '%" + search + "%') || (dueDate like '%" + search + "%') || (details like '%" + search + "%');";
			  response.getWriter().println("<br><br>");
			  PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			  ResultSet rs = preparedStatement.executeQuery();
		 
			while (rs.next()) {
			  String className = rs.getString("className");
			  String assignmentType = rs.getString("assignmentType");
			  String dueDate = rs.getString("dueDate");
			  String details = rs.getString("details");
			  response.getWriter().append("Class Name: " + className + "<br> ");
			  response.getWriter().append("Assignment Type: " + assignmentType + "<br> ");
			  response.getWriter().append("Due Date: " + dueDate + "<br> ");
			  response.getWriter().append("Details: " + details + "<br>");
			  response.getWriter().println("------------------------------------------<br>");
		   }
		} catch (Exception e) {
		     e.printStackTrace();
		}
		response.getWriter().println("</div></form></body></html>");
	}

}
