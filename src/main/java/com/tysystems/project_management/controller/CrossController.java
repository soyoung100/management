package com.tysystems.project_management.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
public class CrossController {

    @RequestMapping(value = "/test.do")
    public ModelAndView test(HttpServletRequest request, Model model, ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ModelAndView", "ModelAndView Test");
        mv.setViewName("test");

        return mv;
    }
}
