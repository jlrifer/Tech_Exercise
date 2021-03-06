

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
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String             url              = "jdbc:mysql://ec2jlrifer.ddns.net:3306/myDB";
	static String             user             = "newremoteuser";
	static String             password         = "password";
	static Connection         connection       = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
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
		response.getWriter().println("<div style=\"display:inline-block; margin-left:35%;\">");
		
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return;
		}
		connection = null;
		  
		try {
		   connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
		   e.printStackTrace();
		   return;
		}
		if (connection == null) {
		   System.out.println("Failed to make connection!");
		}
		
		try {
			String selectSQL = "SELECT className, assignmentType, dueDate, details FROM assignment; ";
			  PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			  ResultSet rs = preparedStatement.executeQuery();
		 
			while (rs.next()) {
			  String className = rs.getString("className");
			  String assignmentType = rs.getString("assignmentType");
			  String dueDate = rs.getString("dueDate");
			  String details = rs.getString("details");
			  response.getWriter().println("Class Name: " + className + " <br>");
			  response.getWriter().println("Assignment Type: " + assignmentType + " <br>");
			  response.getWriter().println("Due Date: " + dueDate + " <br>");
			  response.getWriter().println("Details: " + details + "<br>");
			  response.getWriter().println("------------------------------------------<br>");
		   }
		} catch (SQLException e) {
		     e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
