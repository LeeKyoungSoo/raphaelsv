package com.bethesda.raphaelsv.basic.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/basic")
public class BasicController {
    @RequestMapping(value = "/ingredient")
    public ModelAndView goIngredient(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/pharma/ingredient.html");
        return mav;
    }
}
