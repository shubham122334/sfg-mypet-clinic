package com.springGuru.sfgpetclinic.service;

import com.springGuru.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner , Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
