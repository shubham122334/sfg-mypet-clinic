package com.springGuru.sfgpetclinic.service;

import com.springGuru.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner , Long> {

    Owner findByLastName(String lastName);

}
