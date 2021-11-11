package chap11;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Member> selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL =?",
                getMemberRowMapper(), email);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) " +
                            "VALUES (?, ?, ?, ?)",
                    new String[]{"ID"});
            preparedStatement.setString(1, member.getEmail());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            return preparedStatement;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        assert keyValue != null;
        member.setId(keyValue.longValue());
    }

    public List<Member> selectAll() {
        return jdbcTemplate.query("select * from MEMBER",
                getMemberRowMapper());
    }

    public int count() {
        return jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class);
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail()
        );
    }

    private RowMapper<Member> getMemberRowMapper() {
        return (rs, rowNum) -> new Member(
                rs.getLong("ID"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getTimestamp("REGDATE").toLocalDateTime()
        );
    }
}

