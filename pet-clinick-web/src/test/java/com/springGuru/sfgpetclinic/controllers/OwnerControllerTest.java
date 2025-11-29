package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;


import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController ownerController;

    @Mock
    Model model;

    @Mock
    OwnerService ownerService;

    @Captor
    ArgumentCaptor<Set<Owner>> captor;

    Set<Owner> owners = new HashSet<>();
    MockMvc  mockMvc;
    @BeforeEach
    void setUp() {
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void testMockMvc() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners",owners));
    }
    @Test
    void listOwner()  {
        when(ownerService.findAll()).thenReturn(owners);

        String viewName=ownerController.listOwner(model);
        assertEquals("owners/index", viewName);


        verify(ownerService).findAll();
        verify(model,times(1)).addAttribute(eq("owners"), captor.capture());
        assertEquals(owners.size(),captor.getValue().size());
    }

    @Test
    void displayOwner() throws Exception {

        Long id=2L;
        Owner owner=Owner.builder().id(id).build();
        when(ownerService.findById(id)).thenReturn(owner);

        mockMvc.perform(get("/owners/{ownerId}",id))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner",hasProperty("id",is(2L))));

        verify(ownerService,times(1)).findById(id);

    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFromReturnMany() throws Exception {
        List<Owner> owner1 = Arrays.asList(
                Owner.builder().id(1L).firstName("Shubham").lastName("prajapati").build(),
                Owner.builder().id(2L).firstName("Sonu").lastName("prajapati").build());

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(owner1);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections",hasSize(2)));

    }

    @Test
    void processFindFromReturnOne() throws Exception {
        List<Owner> owner1 = Collections.singletonList(
                Owner.builder().id(1L).firstName("Shubham").lastName("prajapati").build());

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(owner1);

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"));

    }


}