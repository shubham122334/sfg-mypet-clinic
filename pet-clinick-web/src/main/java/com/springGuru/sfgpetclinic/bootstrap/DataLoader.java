package com.springGuru.sfgpetclinic.bootstrap;

import com.springGuru.sfgpetclinic.model.*;
import com.springGuru.sfgpetclinic.service.OwnerService;
import com.springGuru.sfgpetclinic.service.PetTypeService;
import com.springGuru.sfgpetclinic.service.SpecialityService;
import com.springGuru.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;



    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count=petTypeService.findAll().size();
        if(count==0)
            loadData();


    }

    private void loadData() {
        PetType dog=new PetType();
        dog.setName("Dog");

        petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("Dog");

        petTypeService.save(cat);
        System.out.println("PetType added............");

        Speciality radiology=new Speciality();
        radiology.setDescription("radiology");

        Speciality savedRadiology=specialityService.save(radiology);

        Speciality  surgery=new Speciality();
        surgery.setDescription("surgery");

        Speciality savedSurgery=specialityService.save(surgery);

        Speciality dentistry=new Speciality();
        dentistry.setDescription("dentistry");

        Speciality savedDentistry=specialityService.save(dentistry);
        System.out.println("Speciality added........");


        Owner owner1=new Owner();
        owner1.setFirstName("shubham");
        owner1.setLastName("prajapati");
        owner1.setAddress("Dlf phase sec-28");
        owner1.setCity("Gurgao");
        owner1.setTelephone("12343122");

        Pet shubhsPet=new Pet();
        shubhsPet.setPetType(dog);
        shubhsPet.setOwner(owner1);
        shubhsPet.setBirthDate(LocalDate.now());
        shubhsPet.setName("Rasco");
        owner1.getPets().add(shubhsPet);

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("Monu");
        owner2.setLastName("Sharma");
        owner2.setAddress("Noida Sec-16");
        owner2.setCity("Noida");
        owner2.setTelephone("21800189");

        Pet monusPet=new Pet();
        monusPet.setPetType(cat);
        monusPet.setOwner(owner2);
        monusPet.setBirthDate(LocalDate.now());
        monusPet.setName("Suzi");
        owner2.getPets().add(monusPet);

        ownerService.save(owner2);

        System.out.println("Owner Loaded.......");

        Vet vet1=new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);


        vetService.save(vet2);

        System.out.println("Vet Loaded.....");
    }
}
