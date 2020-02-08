import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
               {
		  
                    response.setContentType("text/html");
                    PrintWriter pw = response.getWriter();
                    
                    pw.print("success");
                   
                 try{
		
                    String Uname=request.getParameter("uname");
                    String pwd=request.getParameter("psw");
                  
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                       Statement stmt=con.createStatement();

                       String vsql = "select * from athlete where uname=? and passd=?";
           			
           			
           			PreparedStatement pstmt = con.prepareStatement(vsql);
           			pstmt.setString(1,Uname);
           			pstmt.setString(2, pwd);
           								
           			ResultSet result=pstmt.executeQuery();
           			
           			if(result.next()!=false)
           			{
           				
           				response.sendRedirect("login.html");
           			}
           			
           			else
           			{
           				response.sendRedirect("tournament.html");
           			}
           			
     con.close();
  }




catch(Exception e){
			pw.println(e);
		}
	}
}
