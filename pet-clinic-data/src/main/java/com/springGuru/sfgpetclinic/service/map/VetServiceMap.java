package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Vet;
import com.springGuru.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
    @Override
    public Set<Vet> findAll() {
        return super.findALl();
    }
    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(),object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {

        super.delete(object);
    }
}
