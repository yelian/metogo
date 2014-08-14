package transcational;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;

public class TestInsert {

	@Test
	public void insert(){
		String sql = "insert into team(id, employeetime, workage, name , count) values (?,?,'5','developer','23')";
		Connection conn = Session.getConnection(false);
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, UUID.randomUUID().toString());
			st.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			st.executeUpdate();
			conn.commit();
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
}
