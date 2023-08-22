package com.hyunlabs.simple.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sift/video/list")
    public String videoList() {
        return "/sift/video/list";
    }

    @GetMapping("/sift/image/list/{videoName}")
    public ModelAndView imageList(@PathVariable String videoName) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sift/image/list");
        mv.addObject("videoName", videoName);
        return mv;
    }
}
