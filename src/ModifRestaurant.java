import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.util.Random;
import java.util.StringTokenizer;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModifRestaurant {
	
	private static PreparedStatement preStmt=null;
	private static Statement stmt=null;
	private static String iValues;
	private StringTokenizer tok=null;
	private ResultSet rSet=null;
	
	public ModifRestaurant() {
		
	}
	public ModifRestaurant(Connection conn, Statement stmt) {
		try {
			iValues="insert into Restaurant values(?,?,?,?,?,?,?,?,?)";
			preStmt=conn.prepareStatement(iValues);
			ModifRestaurant.stmt=stmt;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void InsertValues() {//
		try {
			Random rand=new Random();
			String apiurl="http://openapi.seoul.go.kr:8088/51565a7a656373633636764e475464/xml/touristFoodInfo/1/100/";
			URL obj=new URL(apiurl);
			HttpURLConnection con=(HttpURLConnection)obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "USER_AGENT");
			con.getResponseCode();
			
			BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			for(int n=0;n<100;n++) {
				while(true) {
					if(in.readLine().equals("<row>"))
						break;
				}
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setInt(1, Integer.parseInt(tok.nextToken()));
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setString(2, tok.nextToken());
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setString(3, tok.nextToken());
				for(int i=0; i<4; i++)
					in.readLine();
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setString(4, tok.nextToken());
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setString(5, tok.nextToken());
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setString(6, tok.nextToken());
				tok=new StringTokenizer(in.readLine(), "<>");
				tok.nextToken();
				//System.out.println(tok.nextToken());
				preStmt.setString(7, tok.nextToken());
				preStmt.setInt(8, (rand.nextInt(16)+16)*1000);
				preStmt.setInt(9, (rand.nextInt(12)+4)*1000);
				preStmt.executeUpdate();
			}
		}
		catch(MalformedURLException e1) {
			System.out.println(e1.getMessage());
		}
		catch(IOException e2) {
			System.out.println(e2.getMessage());
		}
		catch(SQLException e3) {
			System.out.println(e3.getMessage());
		}
	}//
	public void InsertValues(String rName, String address, String yn1, String yn2, String yn3, String yn4, int min, int max) {
		try {
			rSet=stmt.executeQuery("select max(rId) from Restaurant;");
			rSet.next();
			int maxrId=rSet.getInt("max");
			preStmt.setInt(1, maxrId+1);
			preStmt.setString(2, rName);
			preStmt.setString(3, address);
			preStmt.setString(4, yn1);
			preStmt.setString(5, yn2);
			preStmt.setString(6, yn3);
			preStmt.setString(7, yn4);
			preStmt.setInt(8, max);
			preStmt.setInt(9, min);
			preStmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void UpdateValues(String rName, String address, String yn1, String yn2, String yn3, String yn4, int min, int max) {//식당이름기준
		try {
			String sql="update Restaurant \r\n" + 
					"set address='"+address+"', accessible='"+yn1+"', parking='"+yn2+"', isFlat='"+yn3+"', elevator='"+yn4+"', max="+max+", min="+min+"\r\n" + 
					"where rName='"+rName+"';";
			stmt.executeQuery(sql);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void CreateRestrInq() {
		try {
			stmt.executeUpdate("create table RestrInq as\r\n" + 
					"select rName, address, accessible, isflat, min, max, avg(score) as score\r\n" + 
					"from Restaurant, Review\r\n" + 
					"where Restaurant.rID=Review.rID\r\n" + 
					"group by Restaurant.rID\r\n" + 
					"union\r\n" + 
					"select rName, address, accessible, isflat, min, max, 0\r\n" + 
					"from Restaurant \r\n" + 
					"where not rID in (select distinct(rID) from Review);");
		}
		catch(SQLException e) {//사용자가 종료를 누르지 않아서 이미 존재한다면 drop하고 다시생성
			DropRestrInq();
			try {
				stmt.executeUpdate("create table RestrInq as\r\n" + 
						"select rName, address, accessible, isflat, min, max, avg(score) as score\r\n" + 
						"from Restaurant, Review\r\n" + 
						"where Restaurant.rID=Review.rID\r\n" + 
						"group by Restaurant.rID\r\n" + 
						"union\r\n" + 
						"select rName, address, accessible, isflat, min, max, 0\r\n" + 
						"from Restaurant \r\n" + 
						"where not rID in (select distinct(rID) from Review);");
			}
			catch(SQLException ee) {
				System.out.println(ee.getMessage());
			}
		}
	}
	public void DropRestrInq() {
		try {
			stmt.execute("drop table RestrInq;");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void InquireValues(ArrayList<String> list, String name, String add, boolean wchair, boolean price, String smin, String smax, int score) {
		String sql;
		if(!(name.equals("이름")||name.equals(""))&&!(add.equals("주소")||add.equals(""))) {
			if(wchair==true) {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException ne){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where rName='"+name+"' and address='"+add+"' and min>"+min+" and max<"+max+"\r\n" + 
							"and accessible='Y' and isflat='Y' and score>="+score+";";//1.이름,주소,휠체어,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select * from RestrInq where rName='"+name+"' and address='"+add+"'\r\n" + 
							"and accessible='Y' and isflat='Y' and score>="+score+";";//이름,주소,휠체어,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			else {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where rName='"+name+"' and address='"+add+"'\r\n" + 
							"and min>"+min+" and max<"+max+" and score>="+score+";";//이름,주소,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select * from RestrInq where rName='"+name+"' and address='"+add+"' and score>="+score+";";//이름,주소,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		else if(!name.equals("이름")&&!name.equals("")) {
			if(wchair==true) {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where rName='"+name+"' and min>"+min+" and max<"+max+"\r\n" + 
							"and accessible='Y' and isflat='Y' and score>="+score+";";//이름,휠체어,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select * from RestrInq where rName='"+name+"'\r\n" + 
							"and accessible='Y' and isflat='Y' and score>="+score+";";//이름,휠체어,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			else {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where rName='"+name+"'\r\n" + 
							"and min>"+min+" and max<"+max+" and score>="+score+";";//이름,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select rName, address from RestrInq where address='"+add+"' and score>="+score+";";//이름,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		else if(!add.equals("주소")&&!add.equals("")) {
			if(wchair==true) {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where address='"+add+"' and min>"+min+" and max<"+max+"\r\n" + 
							"and accessible='Y' and isflat='Y' and score>="+score+";";//주소,휠체어,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select * from RestrInq where address='"+add+"'\r\n" + 
							"and accessible='Y' and isflat='Y' and score>="+score+";";//주소,휠체어,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			else {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where address='"+add+"'\r\n" + 
							"and min>"+min+" and max<"+max+" and score>="+score+";";//주소,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select rName, address from RestrInq where address='"+add+"' and score>="+score+";";//주소,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		else {
			if(wchair==true) {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where accessible='Y' and isflat='Y' \r\n" + 
							"and min>"+min+" and max<"+max+" and score>="+score+";";//휠체어,가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select rName, address from RestrInq where accessible='Y' and isflat='Y' and score>="+score+";";//휠체어,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			else {
				if(price==true) {
					int min=0;
					int max=0;
					try {
						min=Integer.parseInt(smin);
						max=Integer.parseInt(smax);
					}
					catch(NumberFormatException e){
						list.add("가격을 바르게 입력하세요");
					}
					sql="select * from RestrInq where min>"+min+" and max<"+max+" and score>="+score+";";//가격,별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					sql="select rName, address from RestrInq where score>="+score+";";//별점
					try {
						rSet=stmt.executeQuery(sql);
						while(rSet.next()) {
							list.add(rSet.getString("rName")+", "+rSet.getString("address"));
						}
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}
	public void GetValues(ArrayList<String> list) {//식당 삭제하기에서 이용
		try {
			rSet=stmt.executeQuery("select rName, address from Restaurant;");
			while(rSet.next()) {
				list.add(rSet.getString("rName")+", "+rSet.getString("address"));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void DeleteValues(ArrayList<String> list) {//식당 삭제하기에서 이용
		try {
			for(String s : list) {
				rSet=stmt.executeQuery("select rID from Restaurant where rName='"+s+"';");
				if(rSet.next())
					stmt.execute("delete from Restaurant where rID="+rSet.getInt("rID")+";");
			}	
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}