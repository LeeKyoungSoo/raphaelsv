package com.bethesda.raphaelsv.etcmng.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/etcmng")
public class EtcmngController {

    @RequestMapping(value = {"/codemng"})
    public ModelAndView goCodemng(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/etcmng/code.html");
        return mav;
    }

    @RequestMapping(value = {"/membermng"})
    public ModelAndView goMembermng(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/etcmng/member.html");
        return mav;
    }

}
