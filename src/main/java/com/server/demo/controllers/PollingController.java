package com.server.demo.controllers;

import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.DateForUpdate;
import com.server.demo.service.DataUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PollingController {

    @Autowired
    DataUpdateService dataUpdateService;

    @PostMapping("/polling/{id}")
    public ResponseEntity polling(@PathVariable Long id, @RequestBody DateForUpdate date){
        try{
            return ResponseEntity.ok(dataUpdateService.polling(id,date));
        }catch (WaiterNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
