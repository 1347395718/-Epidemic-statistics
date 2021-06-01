package com.duing.controller;

import com.duing.bean.DataBean;
import com.duing.service.imp.DataServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DataController {

    @Autowired
    private DataServiceImp dataService;
    @GetMapping("/")
    public String list (Model model){
        model.addAttribute("dataList",dataService.list());
        System.out.println("data:"+dataService.list());
        return "list";
    }

}
