package com.springGuru.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/owners")
public class OwnerController {


    @RequestMapping({"","/","/index"})
    public String listOwner(){

        return "owners/index";
    }
}
