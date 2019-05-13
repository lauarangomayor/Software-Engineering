package com.proyectofinal.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.proyectofinal.web.model.Member;

public class MemberDaoImpl implements MemberDao {
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	  
	@Override
	public List<Member> getUsersByProjectId(int id){
		String sql = "SELECT * FROM projectsxuser INNER JOIN users ON(projectsxuser.userId = users.id) WHERE projectId = '" + id + "'";
		return jdbcTemplate.query(sql, new MemberMapper());
	}
}

class MemberMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Member(rs.getInt("id"), rs.getInt("userId"),rs.getString("role"),rs.getInt("projectId"));
	}
	
}
