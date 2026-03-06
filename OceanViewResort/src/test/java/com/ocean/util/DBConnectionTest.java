package com.ocean.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {

	@Test
	void testConnectionIsOpen() throws SQLException {

	    Connection conn = DBConnection.getConnection();

	    assertFalse(conn.isClosed());
	}
}	