package assignment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveItem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));

		Connection c = null;
		try
		{
		      /*      String url = "jdbc:mysql://localhost/roughdb";
            String username = "root";
            String password = "mypass";*/
        	
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
        	String username = "cs3220stu38";
        	String password = "xxxxxx"; 

			 
			String sql = "DELETE FROM items WHERE id = ?";
			c = DriverManager.getConnection( url, username, password );

			PreparedStatement pstmt = c.prepareStatement(sql); 
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		}

		catch( SQLException e )
		{
			throw new ServletException( e );
		}
		finally
		{
			try
			{
				if( c != null ) c.close();
			}
			catch( SQLException e )
			{
				throw new ServletException( e );
			}
		}
		response.sendRedirect("Inventory");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
