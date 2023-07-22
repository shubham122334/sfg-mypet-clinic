package com.springGuru.sfgpetclinic.repositories;

import com.springGuru.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner , Long> {
}
