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
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import assignment.ItemModel;;

@WebServlet(urlPatterns = "/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;	
       
    public ShoppingCart() {
        super();
    }
    //int totalPrice = 0;
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ItemModel> cartItems = new ArrayList<ItemModel>();
		
		cartItems = (List<ItemModel>)request.getSession().getAttribute("cartItems");
//		for (ItemModel cart : cartItems) {
//			System.out.println("cart quantity: "+cart.getQuantity());
//		}
		
		request.getRequestDispatcher("/WEB-INF/ShoppingCart.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				List<ItemModel> cartItems;
				int numberOfItems = 0;
				Double price = Double.parseDouble(request.getParameter("price"));
				int quantity = Integer.valueOf(request.getParameter("quantity"));
				System.out.println(quantity);
				System.out.println(price);
				Integer id = Integer.valueOf(request.getParameter("id"));
				String name = request.getParameter("name");
				String details="";
				
				Double eachTotal= 0.0;
				eachTotal = quantity*price;
				ItemModel cart = new ItemModel(id, name, price, quantity, details);
				cart.setId(id);
				cart.setName(name);
				cart.setPrice(price);
				cart.setQuantity(quantity);
				cart.setDetails(details);
				cart.setEachTotal(eachTotal);
				
				Connection c = null;
				try
		        {
					String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
					String username = "cs3220stu38";
					String password = "xxxxxx";

					c = DriverManager.getConnection( url, username, password );
		            
		            String sql = "INSERT INTO shopping_cart(id, name, quantity, price, each_total) VALUES(?, ?, ?, ?, ?)";
		            
		            PreparedStatement pstmt = c.prepareStatement(sql); 
		            
		            pstmt.setInt(1, id);
		            pstmt.setString(2, name);
					pstmt.setInt(3, quantity);
					pstmt.setDouble(4, price);
					pstmt.setDouble(5, quantity*price);
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
				
				cartItems = (List<ItemModel>)request.getSession().getAttribute("cartItems");
				
				if(cartItems == null){
					cartItems = new ArrayList<ItemModel>();
					cartItems.add(cart);
					request.getSession().setAttribute("cartItems", cartItems);
				}else{
					cartItems.add(cart);
					request.getSession().setAttribute("cartItems", cartItems);
				}
				numberOfItems = cartItems.size();
				request.getSession().setAttribute("quantity", quantity);
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("numberOfItems", numberOfItems);
				request.getSession().setAttribute("eachTotal", eachTotal);
				double totalPrice=0;
				for (ItemModel itemModel : cartItems) {
					totalPrice += itemModel.getQuantity()*itemModel.getPrice();
				}
				
				request.getSession().setAttribute("total", totalPrice);
				request.getSession().setAttribute("cartItems", cartItems);
				response.sendRedirect("Store");			
	}

}