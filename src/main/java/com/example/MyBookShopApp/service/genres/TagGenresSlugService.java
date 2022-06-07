package com.example.MyBookShopApp.service.genres;


import com.example.MyBookShopApp.data.genres.tags.EmptyTag;
import com.example.MyBookShopApp.data.genres.tags.TagGenresPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagGenresSlugService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagGenresSlugService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Helpers private method get Header is empty list
    private List<TagGenresPage> getTagGenresPage(){
        List<TagGenresPage> headersTagsGenresPage = jdbcTemplate.query("SELECT * FROM TAGS_HEADER_GENRES_PAGE ", (ResultSet rs, int rowNum) -> {
            TagGenresPage tagGenresPage = new TagGenresPage();
            tagGenresPage.setIdTagHeaderGenresPage(rs.getInt("id_tag_header_genres_page"));
            tagGenresPage.setTagHeaderNameGenresPage(rs.getString("tag_header_name_genres_page"));
            tagGenresPage.setTagHeaderCountBooks(rs.getInt("tag_header_count_books"));
            return tagGenresPage;
        });

        return new ArrayList<>(headersTagsGenresPage);
    }

    public List<TagGenresPage> getFilledHeaderTagGenresPage() {
        List<TagGenresPage> tagGenresPageList = getTagGenresPage();
        List<TagGenresPage> tagGenresPageListCopy = new ArrayList<>();
        Map<String, List<EmptyTag>> container = new HashMap<>();
        for (TagGenresPage tagGenresPage : tagGenresPageList) {
            Integer idTagHeader = tagGenresPage.getIdTagHeaderGenresPage();
            List<EmptyTag> emptyTags = jdbcTemplate.query("SELECT * FROM EMPTY_TAGS_GENRES_PAGE where id_tag_header_genres_page=" + idTagHeader, (ResultSet rs, int rowNum)->{
                EmptyTag emptyTag = new EmptyTag();
                emptyTag.setIdEmptyTagGenresPage(rs.getInt("id_empty_tag_genres_page"));
                emptyTag.setEmptyTagName(rs.getString("empty_tag_name_genres_page"));
                emptyTag.setCountBooks(rs.getInt("empty_tag_count_books_genres_page"));
                emptyTag.setIdHeaderTagGenresPage(rs.getInt("id_tag_header_genres_page"));
                return emptyTag;
            });
            if (!emptyTags.isEmpty())
            container.put(tagGenresPage.getTagHeaderNameGenresPage(), emptyTags);
            tagGenresPage.setEmptyTags(emptyTags);
            tagGenresPageListCopy.add(tagGenresPage);
        }
        Map<String, List<EmptyTag>> container2 = new HashMap<>(container);
        Map<String, List<EmptyTag>> container3 = new HashMap<>(container);

        tagGenresPageList = null;

        return tagGenresPageListCopy;
    }
}
