package recognition01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBCドライバの登録
    String server, dbname, url, user, password;// データベースの指定
    Connection con;
    Statement stmt;
    Map<String, Object> lng = new HashMap<>();
    
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
        this.server = "sangi2018.sist.ac.jp";
        this.dbname = "j16000";
        this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "j16000";
        this.password = "sistj16000";
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName (driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void recognition01(int age_min, int age_max, double age_score, int gender, double gender_score) {
		StringBuffer buf = new StringBuffer();
		String student = "1821005";
		buf.append("INSERT INTO `faces`(`age_min`, `age_max`, `age_score`, `gender`, `gender_score`) VALUES ("+ age_min +"," + age_max +","+ age_score +"," + gender +","+ gender_score +");");
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
