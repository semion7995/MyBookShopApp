package com.example.MyBookShopApp.service.tags;
import com.example.MyBookShopApp.data.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsMainService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagsMainService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> getTagsList() {

        List<Tag> tags = new ArrayList<>();

        tags = jdbcTemplate.query("SELECT *FROM TAGS_", new RowMapper<Tag>() {
            @Override
            public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tag tag = new Tag();
                tag.setTag(rs.getString("NAME_"));
                return tag;
            }
        });
        return tags;
    }
}
