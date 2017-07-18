package org.task.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Utility class that fills db with a content size of "ENTRY_QUANTITY"
 */
@Component
public class ContentManager {

	private int ENTRY_QUANTITY = 10;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void fillDb() {

		String sql = "INSERT INTO CONTACTS (ID, NAME) VALUES (?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				String id = Integer.toString(i + 1);
				ps.setString(1, id);
				ps.setString(2, "Mike_" + id);
			}

			@Override
			public int getBatchSize() {
				return ENTRY_QUANTITY;
			}
		});
	}
}
