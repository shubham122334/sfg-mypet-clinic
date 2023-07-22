package com.springGuru.sfgpetclinic.repositories;

import com.springGuru.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends CrudRepository<Vet,Long> {
}
