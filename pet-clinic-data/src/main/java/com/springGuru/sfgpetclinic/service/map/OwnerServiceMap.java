package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Owner;
import com.springGuru.sfgpetclinic.service.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements CrudService<Owner,Long> {

    @Override
    public Set<Owner> findAll() {
        return super.findALl();
    }

    @Override
    public void delete(Owner object) {

        super.delete(object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }


    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(),object);
    }

    @Override
    public void deleteById(Long id) {
         super.deleteById(id);
    }


}
