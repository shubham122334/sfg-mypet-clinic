package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.model.PetType;
import com.springGuru.sfgpetclinic.service.OwnerService;
import com.springGuru.sfgpetclinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController( OwnerService ownerService, PetTypeService petTypeService) {

        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("type")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner populateOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }
}
