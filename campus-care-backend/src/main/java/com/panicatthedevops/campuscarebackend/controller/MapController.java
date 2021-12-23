package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Area;
import com.panicatthedevops.campuscarebackend.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/areas")
public class MapController {
    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public ResponseEntity<List<Area>> getAreas(){
        return ResponseEntity.ok(mapService.getAreas());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Area> getArea(@PathVariable String name){
        return ResponseEntity.ok(mapService.getArea(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteArea(@PathVariable String name){
        mapService.deleteArea(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> createCafeteria(@RequestHeader String name, @RequestHeader int capacity, @RequestHeader String type){
        if(type.equals("Cafeteria"))
            return ResponseEntity.ok(mapService.saveCafeteria(name, capacity));
        else if(type.equals("Smoking Area"))
            return ResponseEntity.ok(mapService.saveSmokingArea(name));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RequestHeader for type " + type + " is invalid.");
    }

    @PatchMapping("/{name}/liveCount")
    public ResponseEntity<Area> setLiveCount(@PathVariable String name, @RequestHeader int liveCount){
        return ResponseEntity.ok(mapService.setLiveCount(liveCount, name));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<?> updateArea(@PathVariable String name, @RequestHeader String newName, @RequestHeader int capacity, @RequestHeader String type){
        if(type.equals("Smoking Area"))
            return ResponseEntity.ok(mapService.updateSmokingArea(name, newName));
        else if(type.equals("Cafeteria"))
            return ResponseEntity.ok(mapService.updateCafeteria(name, newName, capacity));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RequestHeader for type " + type + " is invalid.");
    }
}
