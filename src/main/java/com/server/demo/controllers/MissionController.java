package com.server.demo.controllers;

import com.server.demo.entities.MissionEntity;
import com.server.demo.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionController {
    @Autowired
    MissionService missionService;

    @PostMapping("mission/create")
    public ResponseEntity createMission(@RequestBody MissionEntity missionEntity){
        try{
            return ResponseEntity.ok(missionService.createMission(missionEntity));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }
}
