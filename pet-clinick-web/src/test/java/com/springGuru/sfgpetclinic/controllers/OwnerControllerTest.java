package com.springGuru.sfgpetclinic.controllers;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

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

        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners",owners));
    }
    @Test
    void listOwner() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        String viewName=ownerController.listOwner(model);
        assertEquals("owners/index", viewName);

        ArgumentCaptor<Set<Owner>> captor = ArgumentCaptor.forClass(Set.class);

        verify(ownerService).findAll();
        verify(model,times(1)).addAttribute(eq("owners"), captor.capture());
        assertEquals(owners.size(),captor.getValue().size());
    }

    @Test
    void error() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("notimplemented"));

        verifyNoInteractions(ownerService);
    }
}