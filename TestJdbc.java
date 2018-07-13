package test;

import java.sql.*;

public class TestJdbc {

	public static void main(String[] args) throws SQLException {
		Connection con=null;
		PreparedStatement sta=null;
		ResultSet res=null;
		
		String dbUrl="jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user="student";
		String pass="student";
		
		try{
			con=DriverManager.getConnection(dbUrl, user, pass);
			sta=con.prepareStatement("select * from employees where salary>? and department=?");
			sta.setDouble(1, 25000);
			sta.setString(2, "HR");
			res=sta.executeQuery();
			display(res);
		}catch(Exception e){
			e.printStackTrace();
		}
		res.close();
		sta.close();
		con.close();
	}
	public static void display(ResultSet result) throws SQLException{
		while(result.next()){
			String first_name=result.getString("first_name");
			String last_name=result.getString("last_name");
			Double salary=result.getDouble("salary");
			String department=result.getString("department");
			System.out.printf("%s, %s, %.0f, %s\n",first_name,last_name,salary,department);
		}
	}
}


