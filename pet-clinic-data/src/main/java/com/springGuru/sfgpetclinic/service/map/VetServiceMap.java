package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Speciality;
import com.springGuru.sfgpetclinic.model.Vet;
import com.springGuru.sfgpetclinic.service.SpecialityService;
import com.springGuru.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {


    private final SpecialityService specialtyService;

    public VetServiceMap(SpecialityService specialtyService) {
        this.specialtyService = specialtyService;
    }
    @Override
    public Set<Vet> findAll() {
        return super.findALl();
    }
    @Override
    public Vet save(Vet object) {
        if (object.getSpecialities().size() > 0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(object);
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
