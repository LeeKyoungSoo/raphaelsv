package com.bethesda.raphaelsv.basic.controller;

import lombok.extern.java.Log;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log
@RequestMapping("/basic")
public class BasicController {
    @RequestMapping(value = "/ingredient")
    public ModelAndView goIngredient(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/basic/ingredient.html");
        return mav;
    }

    @RequestMapping(value = "/pharma")
    public ModelAndView goPharma(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/basic/pharma.html");
        return mav;
    }

    @RequestMapping(value = "/product")
    public ModelAndView goProduct(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/basic/product.html");
        return mav;
    }
}
