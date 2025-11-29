package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
@RequestMapping("/owners")
public class OwnerController {

    private  final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedField(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/index","index.html"})
    public String listOwner(Model model){

        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String intiFindForm(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner",ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){

        if(owner.getLastName()==null || owner.getLastName().isBlank()){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        System.out.println(owner.getLastName());
        if(results.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size()==1) {
                Owner resultOwner = results.get(0);
                return "redirect:/owners/"+resultOwner.getId();
        }else{
            model.addAttribute("selections",results);
            return "owners/ownersList";
        }

    }
}
