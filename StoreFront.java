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

import assignment.ItemModel;

@WebServlet(urlPatterns = "/Store")
public class StoreFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//List<ItemModel> cartItems = new ArrayList<ItemModel>();

	public StoreFront() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<ItemModel> items = new ArrayList<ItemModel>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu38";
			String username = "cs3220stu38";
			String password = "";


			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from items where quantity>0");

			while (rs.next()) {
				ItemModel item = new ItemModel(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity")
						,rs.getString("details"));
				items.add(item);
			}



		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		request.getSession().setAttribute("items", items);
		request.getRequestDispatcher("/WEB-INF/StoreFront.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
		
		
}

}