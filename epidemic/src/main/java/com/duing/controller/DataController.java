package com.duing.controller;

import com.duing.bean.DataBean;
import com.duing.bean.GraphBean;
import com.duing.handler.DataHandler;
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
        System.out.println(dataService.list());

        return "list";
    }


    @GetMapping("/display")
    public String display(Model model){


        ArrayList<GraphBean> graphBeans = (ArrayList<GraphBean>) DataHandler.urlGraphBean();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> confirmList = new ArrayList<>();
        ArrayList<String> suspectList = new ArrayList<>();
        for(int i = 0 ; i < graphBeans.size() ; i++){
            GraphBean bean = graphBeans.get(i);
            dateList.add(bean.getDate());
            confirmList.add(new Integer(bean.getConfirm()).toString());
            suspectList.add(new Integer(bean.getSuspect()).toString());
        }

        model.addAttribute("dateList",dateList);
        model.addAttribute("confirmList",confirmList);
        model.addAttribute("suspectList",suspectList);

        System.out.println(dateList);
        System.out.println(confirmList);
        System.out.println(suspectList);

        return "display";
    }




}
