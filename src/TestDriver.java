import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestDriver {
	public static void main(String[] args) {
		String url="jdbc:postgresql:postgres";
		String user="postgres";
		String password="170319";
		
		Connection conn=null;
		Statement stmt=null;
		String sql;
		
		try {
			conn=DriverManager.getConnection(url, user, password);
			stmt=conn.createStatement();
			
			/*stmt.execute("drop table Review");
			stmt.execute("drop table UserInfo");
			stmt.execute("drop table Restaurant");
			stmt.execute("drop trigger if exists ReviewInsert on Review;\r\n" + 
					"drop function reviewinsert;");
			stmt.execute("drop trigger if exists DeleteRestr on Restaurant;\r\n" + 
					"drop function deleterestr;");*/
			
			sql="create table UserInfo(uId int, uName varchar(20), wheelchair char,primary key(uId));";
			stmt.executeUpdate(sql);
			
			sql="create table Restaurant(rId int, rName varchar(50), address varchar(100), accessible char, parking char, isFlat char, elevator char,\r\n" + 
					"					   max int, min int, primary key(rId));";
			stmt.executeUpdate(sql);
			
			sql="create table Review(rId int, uId int, score int,\r\n" + 
					"				   primary key(rId, uId),\r\n" + 
					"				   foreign key(rId) references Restaurant(rId),\r\n" + 
					"				   foreign key(uId) references UserInfo(uId));";
			stmt.executeUpdate(sql);
			
			stmt.execute("create function reviewinsert() returns trigger as $reviewinsert$\r\n" + 
					"begin\r\n" + 
					"	if exists(select * from Review where rID=new.rID and uID=new.uID)THEN\r\n" + 
					"		update Review set score=new.score where rID=new.rID and uID=new.uID;\r\n" + 
					"	else\r\n" + 
					"		return new;\r\n" + 
					"	end if;\r\n" + 
					"	return null;\r\n" + 
					"end;\r\n" + 
					"$reviewinsert$ LANGUAGE plpgsql;\r\n" + 
					"\r\n" + 
					"create trigger ReviewInsert\r\n" + 
					"before insert on Review\r\n" + 
					"for each row\r\n" + 
					"execute PROCEDURE reviewinsert();");
			stmt.execute("create function deleterestr() returns trigger as $deleterestr$\r\n" + 
					"begin\r\n" + 
					"	delete from Review where rID=old.rID;\r\n" + 
					"	return old;\r\n" + 
					"end;\r\n" + 
					"$deleterestr$ LANGUAGE plpgsql;\r\n" + 
					"\r\n" + 
					"create trigger DeleteRestr\r\n" + 
					"before delete on Restaurant\r\n" + 
					"for each row\r\n" + 
					"execute PROCEDURE deleterestr();");
			
			ModifUserInfo iUserInfo=new ModifUserInfo(conn, stmt);
			iUserInfo.InsertValues();
			
			ModifRestaurant iRestaurant=new ModifRestaurant(conn, stmt);
			iRestaurant.InsertValues();
			
			ModifReview iReview=new ModifReview(conn, stmt);
			iReview.executeInputting();
			
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			StartPage window=new StartPage();
			window.open();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}