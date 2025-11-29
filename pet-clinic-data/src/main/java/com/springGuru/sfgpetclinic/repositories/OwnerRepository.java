package com.springGuru.sfgpetclinic.repositories;

import com.springGuru.sfgpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner , Long> {
    Optional<Owner> findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
