package assignment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Checkout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().getAttribute("cartItems");
		request.getRequestDispatcher("/WEB-INF/Checkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String invalid = "invalid input";
		//int quantity = (Integer) request.getSession().getAttribute("quantity");
		int id = (Integer) request.getSession().getAttribute("id");
		//System.out.println("quantity from session :" + quantity);
		Random r = new Random();
		int code = r.nextInt(10000)+1000000;
		request.getSession().setAttribute("code", code);
		List<ItemModel> cartItems = new ArrayList<ItemModel>();

		cartItems = (List<ItemModel>) request.getSession().getAttribute("cartItems");

		if ((name == null || name.trim().length() == 0) || (email == null || email.trim().length() == 0)) {
			request.setAttribute("invalid", invalid);
			doGet(request, response);
			return;
		}

		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
			String username = "cs3220stu38";
			String password = "xxxxxx";
			c = DriverManager.getConnection(url, username, password);
			String sql = "";
			PreparedStatement pstmt;
			boolean check = false;
			for (ItemModel cart : cartItems) {
				int checker = 0;
				int qty_error = 0;
				String sql2 = "SELECT quantity='"+checker+"' from items where id = '" + cart.getId() + "'";
				System.out.println(checker);
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				while (rs.next());
				{
					if (checker < cart.getQuantity()) {
						check = false;
						request.getSession().setAttribute("qty_error",qty_error);
						response.sendRedirect("ShoppingCart");
						return;
					}
					else
					{
						check = true;
						if(request.getSession().getAttribute("qty_error")!=null)
						request.getSession().removeAttribute("qty_error");
					}
				}
			}
			if (check == true) {
				for (ItemModel cart : cartItems) {
					System.out.println("inside checkout quantity: " + cart.getQuantity());
					sql = "UPDATE items SET quantity = quantity - '" + cart.getQuantity() + "'" + " where '"
							+ cart.getId() + "' = items.id";
					pstmt = c.prepareStatement(sql);
					pstmt.executeUpdate();
				}

			}
		}

		catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		System.out.println("successfully added user: " + name + " with email: " + email);
		response.sendRedirect("Done");
		return;
	}

}