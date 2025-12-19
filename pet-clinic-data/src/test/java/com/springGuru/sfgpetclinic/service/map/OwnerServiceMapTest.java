package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    private final Long ownerId=1L;
    private final String firstName="shubham";
    private final String lastName="prajapati";


    @BeforeEach
    void setUp() {
        ownerServiceMap=new OwnerServiceMap(new PetTypeServiceMap(),new PetServiceMap());
        ownerServiceMap.save(Owner.builder().
                id(ownerId)
                .firstName(firstName)
                .lastName(lastName)
                .build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void delete() {
        Owner owner=ownerServiceMap.findById(ownerId);
        ownerServiceMap.delete(owner);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Owner owner=ownerServiceMap.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void save() {
        Owner owner=Owner.builder().id(2L).build();
        ownerServiceMap.save(owner);
        assertEquals(2,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(2L);
        assertEquals(1,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner=ownerServiceMap.findByLastName(lastName);
        assertEquals(1L,owner.getId());
        assertEquals(firstName,owner.getFirstName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner=ownerServiceMap.findByLastName("foo");
        assertNull(owner);
    }
}