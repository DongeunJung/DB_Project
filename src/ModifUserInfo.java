import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ModifUserInfo {

	private static PreparedStatement preStmt=null;
	private static Statement stmt=null;
	private static int uId=0;
	private final String YN="YN";
	private static String iValues;
	private ResultSet rSet=null;
	
	public ModifUserInfo() {
		
	}
	public ModifUserInfo(Connection conn, Statement stmt) {
		try {
			iValues="insert into UserInfo values(?,?,?)";
			preStmt=conn.prepareStatement(iValues);
			ModifUserInfo.stmt=stmt;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void InsertValues() {
		try(BufferedReader reader=new BufferedReader(new FileReader("UserInfo.txt"))){
			Random rand=new Random();
			String name;
			char YORN;
			while(true) {
				uId++;
				YORN=YN.charAt(rand.nextInt(2));
				name=reader.readLine();
				if(name==null)
					break;
				try {
					preStmt.setInt(1, uId);
					preStmt.setString(2, name);
					preStmt.setString(3, Character.toString(YORN));
					preStmt.executeUpdate();
				}
				catch(SQLException s) {
					System.out.println(s.getMessage());
				}
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void InsertValues(String uName, char YN) {
		try {
			preStmt.setInt(1, uId++);
			preStmt.setString(2, uName);
			preStmt.setString(3, Character.toString(YN));
			preStmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean CheckUserInfo(String uName) {//로그인 페이지에서 유저가 존재하면 true 아니면 false//ServicePage 오픈
		int uId=0;
		try {
			rSet=stmt.executeQuery("select uId from UserInfo where uName='"+uName+"';");
			if(rSet.next()) {
				uId=rSet.getInt("uId");
				StartPage.userID=uId;
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		if(uId>0)
			return true;
		return false;
	}
	public int getUserID(String uName) {
		int result=0;
		try {
			rSet=stmt.executeQuery("select uId from UserInfo where uName='"+uName+"';");
			while(rSet.next())
				result=rSet.getInt("uId");/////////////////////////////////////////////////////////////////////////////////////////
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
