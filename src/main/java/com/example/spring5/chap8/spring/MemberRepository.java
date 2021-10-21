package com.example.spring5.chap8.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberRepository {

    private JdbcTemplate jdbcTemplate;

    public MemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
            "select * from MEMBER where EMAIL = ?",
            new MemberRowMapper(),
            email
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            // 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
            PreparedStatement pstmt = con.prepareStatement(
                "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
                    "values (?, ?, ?, ?)",
                new String[]{"ID"});
            // 인덱스 파라미터 값 설정
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4,
                Timestamp.valueOf(member.getRegisterDateTime()));
            // 생성한 PreparedStatement 객체 리턴
            return pstmt;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update(
            "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
            member.getName(), member.getPassword(), member.getEmail());
    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER",
            new MemberRowMapper());
        return results;
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject(
            "select count(*) from MEMBER", Integer.class);
        return count;
    }

}
