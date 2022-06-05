package com.server.demo.controllers;


import com.server.demo.model.AchievementsForCreate;
import com.server.demo.model.AchievementsForWeb;
import com.server.demo.model.Mission;
import com.server.demo.model.MissionForCreate;
import com.server.demo.service.AchievementsService;
import com.server.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AchievementsController {

    @Autowired
    AchievementsService achievementsService;

    @GetMapping("achievements/all")
    public ResponseEntity allAchievements(){
        try{
            return ResponseEntity.ok(achievementsService.allAchievements());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("achievements/{id}")
    public ResponseEntity getAchievements(@PathVariable Long id){
        try{
            return ResponseEntity.ok(achievementsService.getAchievement(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @PutMapping("achievements/update/{id}")
    public ResponseEntity updateMission(@PathVariable Long id,@RequestBody AchievementsForWeb achievements){
        try{
            return ResponseEntity.ok(achievementsService.updateAchievements(id,achievements));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @DeleteMapping("achievements/delete/{id}")
    public ResponseEntity deleteMission(@PathVariable Long id){
        try{
            return ResponseEntity.ok(achievementsService.deleteAchievements(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @PostMapping("achievements/create")
    public ResponseEntity createMission(@RequestBody AchievementsForCreate achievementsForCreate){
        try{
            return ResponseEntity.ok(achievementsService.createAchievements(achievementsForCreate));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

}
