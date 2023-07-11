package com.springGuru.sfgpetclinic.bootstrap;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.model.Vet;
import com.springGuru.sfgpetclinic.service.OwnerService;
import com.springGuru.sfgpetclinic.service.VetService;
import com.springGuru.sfgpetclinic.service.map.OwnerServiceMap;
import com.springGuru.sfgpetclinic.service.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(){
        this.vetService =new VetServiceMap();
        ownerService=new OwnerServiceMap();

    }
    @Override
    public void run(String... args) throws Exception {
        Owner owner1=new Owner();
        owner1.setId(1L);
        owner1.setFirstName("shubham");
        owner1.setLastName("prajapati");

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Monu");
        owner2.setLastName("Sharma");
        ownerService.save(owner2);

        System.out.println("Owner Loaded.......");

        Vet vet1=new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setLastName("Jessie");
        vet2.setLastName("Porter");
        vet2.setId(2L);

        vetService.save(vet2);

        System.out.println("Vet Loaded.....");



    }
}
