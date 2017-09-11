

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
			String theUserName = "user%";
	   		response.getWriter().println(selectSQL + "<br>");
			  response.getWriter().println("------------------------------------------<br>");
			  PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			  preparedStatement.setString(1, theUserName);
			  ResultSet rs = preparedStatement.executeQuery();
		 
			while (rs.next()) {
			  String id = rs.getString("ID");
			  String username = rs.getString("MYUSER");
			  String email = rs.getString("EMAIL");
			  String phone = rs.getString("PHONE");
			  response.getWriter().append("USER ID: " + id + ", ");
			  response.getWriter().append("USER NAME: " + username + ", ");
			  response.getWriter().append("USER EMAIL: " + email + ", ");
			  response.getWriter().append("USER PHONE: " + phone + "<br>");
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
