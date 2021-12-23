package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Area;
import com.panicatthedevops.campuscarebackend.entity.Cafeteria;
import com.panicatthedevops.campuscarebackend.entity.SmokingArea;
import com.panicatthedevops.campuscarebackend.exception.AreaAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.AreaNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    private final AreaRepository areaRepository;

    @Autowired
    public MapService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Area saveCafeteria(String name, int capacity ){
        if(areaRepository.existsById(name)){
            throw new AreaAlreadyExistsException("Area with name " + name + " already exists");
        }
        else{
            return areaRepository.save(new Cafeteria(name, 0, capacity));
        }
    }

    public Area saveSmokingArea(String name){
        if(areaRepository.existsById(name)){
            throw new AreaAlreadyExistsException("Area with name " + name + " already exists");
        }
        else{
            return areaRepository.save(new SmokingArea(name, 0));
        }
    }

    public Area setLiveCount(int count, String name){
        if(!areaRepository.existsById(name)){
            throw new AreaNotFoundException("Area with name " + name + " doesnt exist");
        }
        else{
            Area area = areaRepository.findById(name).get();
            area.setLiveCount(count);
            return areaRepository.save(area);
        }
    }

    public void deleteArea(String name){
        if(!areaRepository.existsById(name)){
            throw new AreaNotFoundException("Area with name " + name + " doesnt exist");
        }
        else{
            areaRepository.deleteById(name);
        }
    }

    public Area updateCafeteria(String oldName, String newName, int capacity){
        if(!areaRepository.existsById(oldName)){
            throw new AreaNotFoundException("Area with name " + oldName + " doesnt exist");
        }
        else{
            Area area = areaRepository.findById(oldName).get();
            Cafeteria cafeteria = new Cafeteria(newName, area.getLiveCount(), capacity);
            areaRepository.deleteById(oldName);
            return areaRepository.save(cafeteria);
        }
    }

    public Area updateSmokingArea(String oldName, String newName){
        if(!areaRepository.existsById(oldName)){
            throw new AreaNotFoundException("Area with name " + oldName + " doesnt exist");
        }
        else{
            Area smokingArea = areaRepository.findById(oldName).get();
            SmokingArea newArea = new SmokingArea(newName, smokingArea.getLiveCount());
            areaRepository.deleteById(oldName);
            return areaRepository.save(newArea);
        }
    }

    public List<Area> getAreas(){
        return areaRepository.findAll();
    }

    public Area getArea(String name){
        if(!areaRepository.existsById(name)){
            throw new AreaNotFoundException("Area with name " + name + " doesnt exist");
        }
        else{
            return areaRepository.findById(name).get();
        }
    }

}
