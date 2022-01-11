package com.server.demo.controllers;

import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.ProceedsMissionEntity;
import com.server.demo.model.MissionForCreate;
import com.server.demo.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionController {
    @Autowired
    MissionService missionService;

    @PostMapping("mission/create")
    public ResponseEntity createMission(@RequestBody MissionForCreate missionForCreate){
        try{
            return ResponseEntity.ok(missionService.createMission(missionForCreate));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("mission/all")
    public ResponseEntity allMissions(){
        try{
            return ResponseEntity.ok(missionService.allMissions());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("mission/getAll")
    public ResponseEntity getAllMissions(){
        try{
            return ResponseEntity.ok(missionService.getAllMission() );
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @DeleteMapping("mission/delete/{id}")
    public ResponseEntity deleteMission(@PathVariable Long id){
        try{
            return ResponseEntity.ok(missionService.deleteMission(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }
}
