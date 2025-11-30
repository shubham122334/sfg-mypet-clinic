package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.model.Pet;
import com.springGuru.sfgpetclinic.model.Visit;
import com.springGuru.sfgpetclinic.service.OwnerService;
import com.springGuru.sfgpetclinic.service.PetService;
import com.springGuru.sfgpetclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    @Mock
    Model model;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        Pet pet = Pet.builder().name("Kitti").visits(new HashSet<>()).build();
        pet.setId(1L);
        when(petService.findById(anyLong())).thenReturn(pet);
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/visits/new",1,1))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("visit"));

        verify(petService,times(1)).findById(anyLong());
        verifyNoMoreInteractions(visitService);
    }

    @Test
    void processNewVisitForm() throws Exception {
        Pet pet = Pet.builder().name("Kitti").visits(new HashSet<>()).build();
        pet.setId(1L);
        when(petService.findById(anyLong())).thenReturn(pet);
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        when(visitService.save(ArgumentMatchers.any())).thenReturn(Visit.builder().date(LocalDate.now()).build());

        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/visits/new",1,1)
                        .param("date", "2025-11-30") // example date
                        .param("description", "Regular Checkup")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"));

        verify(visitService,times(1)).save(ArgumentMatchers.any());
    }
}