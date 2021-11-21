package com.bethesda.raphaelsv.incom.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/incom")
public class IncomController {
    @RequestMapping(value = "/support")
    public ModelAndView goSupport(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/incom/support.html");
        return mav;
    }

    @RequestMapping(value = "/incoming")
    public ModelAndView goIncoming(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/incom/incoming.html");
        return mav;
    }

    @RequestMapping(value = "/incomjego")
    public ModelAndView goIncomjego(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/incom/incomjego.html");
        return mav;
    }

    @RequestMapping(value = "/release")
    public ModelAndView goRelease(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/incom/release.html");
        return mav;
    }


    @RequestMapping(value = "/abrogation")
    public ModelAndView goAbrogation(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/incom/abrogation.html");
        return mav;
    }

    @RequestMapping(value = "/attain")
    public ModelAndView goAttain(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/incom/attain.html");
        return mav;
    }
}
