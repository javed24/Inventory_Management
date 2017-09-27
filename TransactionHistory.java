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

import homework.ToDoList;

@WebServlet("/History")
public class TransactionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TransactionHistory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ItemModel> transactions = new ArrayList<ItemModel>();
		List<ItemModel> allTotalObj = new ArrayList<ItemModel>();
		Connection c = null;
		try
        {
		     
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
        	String username = "cs3220stu38";
        	String password = "xxxxxx"; 

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            //ResultSet rs = stmt.executeQuery( "select * from shopping_cart" );
            ResultSet rs = stmt.executeQuery("SELECT name, SUM( each_total ) AS price, SUM( quantity ) AS quantity FROM shopping_cart GROUP BY name");
            		
            
            while( rs.next() ){ //taking each row in the result
            	ItemModel transaction = new ItemModel();
            	//transaction.setId(rs.getInt("id"));
            	transaction.setName(rs.getString("name"));
            	transaction.setQuantity(rs.getInt("quantity"));
            	transaction.setPrice(rs.getDouble("price"));
            	//transaction.setEachTotal(rs.getDouble("each_total"));
            	//transaction.setAllTotal(rs.getDouble("Price"));
            	transactions.add(transaction);
            }
            
            ResultSet rs2 = stmt.executeQuery("SELECT SUM(each_total) as each_total FROM shopping_cart");
            while(rs2.next()){
            	ItemModel transaction2 = new ItemModel();
            	transaction2.setAllTotal(rs2.getDouble("each_total"));
            	allTotalObj.add(transaction2);
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
		Double allTotal=0.0; 
		request.getSession().setAttribute("transactions", transactions);
		for (ItemModel item : allTotalObj) {
			allTotal = item.getAllTotal();
			System.out.println("all total: "+item.getAllTotal());
		}
		request.getSession().setAttribute("allTotalObj", allTotal);
		request.getRequestDispatcher("/WEB-INF/TransactionHistory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
