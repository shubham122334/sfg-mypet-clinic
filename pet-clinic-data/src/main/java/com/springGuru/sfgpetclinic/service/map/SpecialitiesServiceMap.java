package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Speciality;
import com.springGuru.sfgpetclinic.service.SpecialitiesService;

import java.util.Set;

public class SpecialitiesServiceMap extends AbstractMapService<Speciality,Long> implements SpecialitiesService {
    @Override
    public Set<Speciality> findAll() {
        return super.findALl();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
