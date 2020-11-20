import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ModifReview {
	
	private static Statement stmt=null;
	private static PreparedStatement preStmt=null;
	private static String iValues;
	
	public ModifReview() {
		
	}
	public ModifReview(Connection conn, Statement stmt) {
		try {
			ModifReview.stmt=stmt;
			iValues="insert into Review values(?, ?, ?)";
			preStmt=conn.prepareStatement(iValues);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void executeInputting() {
		List<Integer> list=new ArrayList<>();
		String sql;
		sql="select rID from Restaurant;";
		try {
			ResultSet rSet=null;
			rSet=stmt.executeQuery(sql);
			while(rSet.next()) {
				list.add(rSet.getInt("rID"));
			}
			for(int i=0; i<100; i++) {
				preStmt.setInt(1,list.get((int)(Math.random()*100)));
				preStmt.setInt(2, i+1);
				preStmt.setInt(3, (int)(Math.random()*6));
				preStmt.executeUpdate();
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void InsertValues(String rName, String score) {
		String sql1;
		String sql2;
		sql1="select rId from Restaurant where rName='"+rName+"'";
		sql2="select uId from UserInfo where uName='"+StartPage.username+"'";
		try {
			ResultSet rSet=null;
			rSet=stmt.executeQuery(sql1);
			rSet.next();
			int rId=rSet.getInt(1);
			rSet=stmt.executeQuery(sql2);
			rSet.next();
			int uId=rSet.getInt(1);
			preStmt.setInt(1, rId);
			preStmt.setInt(2, uId);
			preStmt.setInt(3, Integer.parseInt(score));
			preStmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
