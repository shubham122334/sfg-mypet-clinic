package com.springGuru.sfgpetclinic.springdatajpa;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.repositories.OwnerRepository;
import com.springGuru.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImpTest {

    @Mock
    OwnerRepository ownerRepository;


    @InjectMocks
    OwnerServiceImp ownerServiceImp;

    Owner returnOwner;
    @BeforeEach
    void setUp(){
        returnOwner = Owner.builder().id(1L).lastName("prajapati").build();
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> result = ownerServiceImp.findAll();
        assertEquals(2,result.size());
        verify(ownerRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner result = ownerServiceImp.findById(1L);
        assertEquals(1,result.getId());
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner result = ownerServiceImp.save(returnOwner);
        assertNotNull(result);
        assertEquals(1L,result.getId());
        verify(ownerRepository,times(1)).save(any());
    }

    @Test
    void delete() {
        ownerServiceImp.delete(returnOwner);

        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerServiceImp.deleteById(1L);
        verify(ownerRepository,times(1)).deleteById(1L);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(Optional.of(returnOwner));
        Owner result = ownerServiceImp.findByLastName("prajapati");
        assertEquals(1L,result.getId());
    }
}