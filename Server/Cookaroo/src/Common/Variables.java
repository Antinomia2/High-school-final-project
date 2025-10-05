package Common;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Variables {
	public static Statement getStmt() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1/cookaroo?"+"user=Liao&password=Liao").createStatement();
	}
}