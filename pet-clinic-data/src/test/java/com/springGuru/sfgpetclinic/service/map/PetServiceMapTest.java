package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {


    PetServiceMap petServiceMap;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> pet=petServiceMap.findAll();
        assertNotNull(pet);
        assertEquals(1, pet.size());

    }

    @Test
    void findById() {
        Pet pet=petServiceMap.findById(1L);
        assertEquals(1L, pet.getId());
    }

    @Test
    void save() {
        Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet2);
        assertEquals(id, savedPet.getId());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(1L);
        assertNull(petServiceMap.findById(1L));
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(1L));
        assertNull(petServiceMap.findById(1L));
    }
}