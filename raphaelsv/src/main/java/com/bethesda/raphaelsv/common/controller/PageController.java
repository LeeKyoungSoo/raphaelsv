package com.bethesda.raphaelsv.common.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/")
public class PageController {
    @RequestMapping(value = {"/"})
    public ModelAndView goMain(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/welcome.html");
        return mav;
    }

    @RequestMapping(value = {"/pharma/ingredient"})
    public ModelAndView goIngredient(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/pharma/ingredient.html");
        return mav;
    }
}

