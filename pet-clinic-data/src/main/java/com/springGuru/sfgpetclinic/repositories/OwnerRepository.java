package com.springGuru.sfgpetclinic.repositories;

import com.springGuru.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner , Long> {
    Optional<Owner> findByLastName(String lastName);
}
