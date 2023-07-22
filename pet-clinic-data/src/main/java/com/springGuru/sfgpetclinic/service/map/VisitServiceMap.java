package com.springGuru.sfgpetclinic.service.map;

import com.springGuru.sfgpetclinic.model.Visit;
import com.springGuru.sfgpetclinic.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class VisitServiceMap extends AbstractMapService<Visit,Long> implements VisitService {


    @Override
    public Set<Visit> findAll() {
        return super.findALl();
    }

    @Override
    public Visit save(Visit visit) {
       if(visit.getPet()==null || visit.getPet().getOwner()==null || visit.getPet().getId()==null
          || visit.getPet().getOwner().getId()==null){
           throw new RuntimeException("these records are required");
       }

        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {

        super.delete(object);
    }
}
