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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.ToDoList;

@WebServlet("/Inventory")
public class InventoryManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InventoryManager() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		 try
	        {
	            Class.forName( "com.mysql.jdbc.Driver" );
	        }
	        catch( ClassNotFoundException e )
	        {
	            throw new ServletException( e );
	        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			List<ItemModel> items = new ArrayList<ItemModel>();
			Connection c = null;
			try
	        {
			      /*      String url = "jdbc:mysql://localhost/roughdb";
	            String username = "root";
	            String password = "mypass";*/
	        	
		            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
	        	String username = "cs3220stu38";
	        	String password = "xxxxxx"; 


	            c = DriverManager.getConnection( url, username, password );
	            
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from items");

	            
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
			request.getRequestDispatcher("/WEB-INF/Inventory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String details = request.getParameter("details");
		
		Connection c = null;
		try
        {
		      /*      String url = "jdbc:mysql://localhost/roughdb";
            String username = "root";
            String password = "mypass";*/
        	
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
        	String username = "cs3220stu38";
        	String password = "w5woPb7Z"; 

            c = DriverManager.getConnection( url, username, password );
            
            String sql = "insert into items (name, price, details, quantity) values (?,?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql); 
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, details);
            pstmt.setInt(4, quantity);
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
		doGet(request, response);
		return;
	}

}
