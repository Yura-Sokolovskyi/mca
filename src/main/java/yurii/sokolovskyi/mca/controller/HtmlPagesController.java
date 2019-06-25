package yurii.sokolovskyi.mca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPagesController {

    @RequestMapping("index")
    public String category() {
        return "index.html";
    }


}
