package com.freetalk.cherokee.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    @RequestMapping("/index")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
