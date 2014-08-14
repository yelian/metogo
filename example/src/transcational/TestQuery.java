package transcational;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import transcational.model.Team;
import transcational.util.DataMapping;
import org.junit.Test;

public class TestQuery {
	
	@Test
	public void testGetConnection(){
		Session.getConnection();
		System.out.println("true");
		Session.close();
	}
	
	@Test
	public void query(){
		String sql = "select * from team";
		Connection conn = Session.getConnection();
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			new DataMapping<Team>().getDate(Team.class, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
