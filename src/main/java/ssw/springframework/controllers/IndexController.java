package ssw.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index0(){
        return "index";
    }

    @RequestMapping("/index")
    String index1(){
        return "index";
    }

    @RequestMapping("/people")
    String people(){
        return "people";
    }

    @RequestMapping("/personform")
    String personform(){
        return "personform";
    }

    @RequestMapping("/personshow")
    String personshow(){
        return "personshow";
    }
}
