package com.springGuru.sfgpetclinic.springdatajpa;

import com.springGuru.sfgpetclinic.model.Pet;
import com.springGuru.sfgpetclinic.repositories.PetRepository;
import com.springGuru.sfgpetclinic.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets=new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {

        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        petRepository.deleteById(aLong);
    }
}
