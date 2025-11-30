package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.model.Pet;
import com.springGuru.sfgpetclinic.model.PetType;
import com.springGuru.sfgpetclinic.service.OwnerService;
import com.springGuru.sfgpetclinic.service.PetService;
import com.springGuru.sfgpetclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetTypeService petTypeService;

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petTypes;
    @BeforeEach
    void setUp() {
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().name("cat").build());
        petTypes.add(PetType.builder().name("dog").build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initCreationForm() throws Exception {


        owner = Owner.builder().id(1L).pets(new HashSet<>()).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/{ownerId}/pets/new",1))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("types"));
    }

    @Test
    void processCreationForm() throws Exception {
        owner = Owner.builder().id(1L).pets(new HashSet<>()).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.save(ArgumentMatchers.any())).thenReturn(Pet.builder().name("kitti").build());
        mockMvc.perform(post("/owners/{ownerId}/pets/new",1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"));

        verify(petService,times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdatePetForm() throws Exception {
        Pet pet = Pet.builder().name("kitti").build();
        pet.setId(1L);
        owner = Owner.builder().id(1L).pets(new HashSet<>(Set.of(pet))).build();

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit",1,1))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("owner"));

        verify(petService,times(1)).findById(anyLong());
    }

    @Test
    void processUpdatePetForm() throws Exception {
        Pet pet = Pet.builder().name("kitti").petType(petTypes.iterator().next()).build();
        pet.setId(1L);
        owner = Owner.builder().id(1L).pets(new HashSet<>(Set.of(pet))).build();

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.save(ArgumentMatchers.any())).thenReturn(Pet.builder().name("kitti").build());

        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit",1,1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"));

        verify(petService,times(1)).save(ArgumentMatchers.any());
    }
}