package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/owners")
public class OwnerController {

    private  final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index"})
    public String listOwner(Model model){

        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
}
