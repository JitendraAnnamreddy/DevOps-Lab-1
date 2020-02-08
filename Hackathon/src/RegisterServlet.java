import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RegisterServlet extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print("success");
		
		try {
			
			//System.out.println("hai");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");  
			Statement stmt=con.createStatement(); 
			
			pw.print("into try!!!");
			
               String vsql = "insert into athlete values(?,?,?,?,?,?,?,?)";
			
			String uname,passd,fname,lname,sex,age,address,email;
			
			uname=request.getParameter("uname");
			passd=request.getParameter("pws");
			fname=request.getParameter("fname");
			lname=request.getParameter("lname");
			sex=request.getParameter("sex");
			age=request.getParameter("age");
			address=request.getParameter("address");
			email=request.getParameter("email");
			
			
               
			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1,uname);
			pstmt.setString(2,passd);
			pstmt.setString(3,fname);
			pstmt.setString(4,lname);
			pstmt.setString(5,sex);
			pstmt.setString(6,sex);
			pstmt.setString(7,address);
			pstmt.setString(8,email);
			
			
								
			int result=pstmt.executeUpdate();
			
			if(result==1)
			{
				response.sendRedirect("login.html");
				//pw.print("success");
			}
			
			else
			{
				pw.print("no Success");
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
