package com.hyunlabs.simple.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class EditController {

    public Map<String, Object> map;

    @GetMapping("/edit/regist")
    public String regist() {
        return "/edit/regist";
    }

    @PostMapping("/edit/insert")
    @ResponseBody
    public String insert(@RequestBody Map<String, Object> map) {

        System.out.println(map);

        this.map = map;

        return "";
    }

    @GetMapping("/edit/detail")
    public ModelAndView detail() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/edit/detail");
        mv.addObject("mdMap", this.map);

        return mv;
    }

    @GetMapping("/edit/modify")
    public ModelAndView modify() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/edit/modify");
        mv.addObject("mdMap", this.map);

        return mv;
    }
}
