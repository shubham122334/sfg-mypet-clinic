package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;


import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController ownerController;

    @Mock
    Model model;

    @Mock
    OwnerService ownerService;



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

        ArgumentCaptor<Set<Owner>> captor=ArgumentCaptor.forClass(Set.class);
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

    @Test
    void initCreateForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"));

        verify(ownerService,times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateForm() throws Exception {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
//        mockMvc.perform(get("/owners/{id}/edit",1))
//                .andExpect(status().isOk())
//                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
//                .andExpect(model().attribute("owner",hasProperty("id",is(1L))));

        ArgumentCaptor<Owner> captor=ArgumentCaptor.forClass(Owner.class);
        String viewName=ownerController.initUpdateOwnerForm(owner.getId(),model);

        assertEquals("owners/createOrUpdateOwnerForm",viewName);

        verify(model,times(1)).addAttribute(eq("owner"),captor.capture());

        verify(ownerService,times(1)).findById(anyLong());

        assertEquals(owner.getId(),captor.getValue().getId());
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).telephone("8585981780").build());
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).telephone("895453").build());

        mockMvc.perform(post("/owners/{ownerId}/edit",1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"));

        verify(ownerService,times(1)).save(ArgumentMatchers.any());
    }

}