package com.jecyhw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jecyhw on 16-10-31.
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
}
