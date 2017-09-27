package assignment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Details() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		List<ItemModel> items = new ArrayList<ItemModel>();
		Connection c = null;
		try
        {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
			String username = "cs3220stu38";
			String password = "xxxxxx";

            c = DriverManager.getConnection( url, username, password );
            
            
			 Statement stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM items WHERE id = '"+id+"'");
            
            while( rs.next() ){ //taking each row in the result
            	ItemModel item = new ItemModel();
            	item.setId(rs.getInt("id"));
            	item.setName(rs.getString("name"));
            	item.setPrice(rs.getDouble("price"));
            	item.setDetails(rs.getString("details"));
            	item.setQuantity(rs.getInt("quantity"));
            	items.add(item);
            }
            	            
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
		request.setAttribute("items", items);
		request.getRequestDispatcher("/WEB-INF/Details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}