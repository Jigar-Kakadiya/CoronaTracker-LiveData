package com.tracker.coronavirustracker.controller;

import com.tracker.coronavirustracker.coronavirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private coronavirusService coronavirusService;
    @RequestMapping("/index")
    public String Index(Model model){
        model.addAttribute("locationStates",coronavirusService.getAllStates());
        return "index";
    }
}
