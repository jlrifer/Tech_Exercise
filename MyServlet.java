

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Object;

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
		response.getWriter().println("<head><title>Assignment List</title></head>");
		response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
		  
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
			String selectSQL = "SELECT * FROM assignment ";
	   		response.getWriter().println(selectSQL + "<br>");
			  response.getWriter().println("------------------------------------------<br>");
			  PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			  ResultSet rs = preparedStatement.executeQuery();
		 
			while (rs.next()) {
			  String className = rs.getString("className");
			  String assignmentType = rs.getString("assignemtType");
			  String dueDate = rs.getString("dueDate");
			  String details = rs.getString("details");
			  response.getWriter().append("Class Name: " + className + ", ");
			  response.getWriter().append("Assignment Type: " + assignmentType + ", ");
			  response.getWriter().append("Due Date: " + dueDate + ", ");
			  response.getWriter().append("Details: " + details + "<br>");
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

	private void searchAssignment(String input){ 
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
			String selectSQL = "select * from assignment where (className like '%of%') || (assignmentType like '%sof%') || (dueDate like '%09%') || (details like '%sof');";
	   		response.getWriter().println(selectSQL + "<br>");
			  response.getWriter().println("------------------------------------------<br>");
			  PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			  ResultSet rs = preparedStatement.executeQuery();
		 
			while (rs.next()) {
			  String className = rs.getString("className");
			  String assignmentType = rs.getString("assignemtType");
			  String dueDate = rs.getString("dueDate");
			  String details = rs.getString("details");
			  response.getWriter().append("Class Name: " + className + ", ");
			  response.getWriter().append("Assignment Type: " + assignmentType + ", ");
			  response.getWriter().append("Due Date: " + dueDate + ", ");
			  response.getWriter().append("Details: " + details + "<br>");
		   }
		} catch (SQLException e) {
		     e.printStackTrace();
		}
	}

	private void addAssignment(String className, String assignmentType, String dueDate, String details){ 
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
			String selectSQL = "insert into assignment (className, assignmentType, dueDate, details) values (\"Windows Admin\", \"Homework\", \"09/30/2017\", \"Lab\");";
	   		response.getWriter().println(selectSQL + "<br>");
			  response.getWriter().println("------------------------------------------<br>");
			  PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			  preparedStatement.setString(1, theUserName);
			  ResultSet rs = preparedStatement.executeQuery();
			  response.getWriter().println(rs);
		} catch (SQLException e) {
		     e.printStackTrace();
		}
	}
}
