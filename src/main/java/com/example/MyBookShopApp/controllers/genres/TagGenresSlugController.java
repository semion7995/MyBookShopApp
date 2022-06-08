package com.example.MyBookShopApp.controllers.genres;

import com.example.MyBookShopApp.service.genres.TagGenresSlugService;
import com.example.MyBookShopApp.util.DatePlasecholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/genres/slug")
public class TagGenresSlugController {
    private final TagGenresSlugService tagGenresSlugService;

    @Autowired
    public TagGenresSlugController(TagGenresSlugService tagGenresSlugService) {
        this.tagGenresSlugService = tagGenresSlugService;
    }

    @ModelAttribute("getServerTime")
    public String getServerTime(){
        return new SimpleDateFormat(DatePlasecholder.PATTERN_DATE, DatePlasecholder.LOCALE).format(new Date());
    }
    @ModelAttribute("messageTemplate")
    public String getMessageTemplate(){
        return "searchbar.placeholder2";
    }


    @GetMapping
    public String getGenresSlugPage(@RequestParam String tagType, @RequestParam String id, @RequestParam String headerName, @RequestParam String lang ,Model model){
        model.addAttribute("headerTagName", headerName);
        model.addAttribute("lang", lang);
        if (tagType.equalsIgnoreCase("headerTag")){
            tagGenresSlugService.getHeaderTagFindByid(id);
        }
        else if (tagType.equalsIgnoreCase("emptyTag")){
            new String();
        }
        return "genres/slug";
    }

}
