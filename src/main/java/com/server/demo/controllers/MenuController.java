package com.server.demo.controllers;

import com.server.demo.model.Dish;
import com.server.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("dishes")
    public ResponseEntity checkDishes(@RequestBody ArrayList<Dish> dish){
        try{
            return ResponseEntity.ok(menuService.findDishes(dish));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @PutMapping("dishes/goList/{id}")
    public ResponseEntity goList(@PathVariable Long id, @RequestBody Dish dish){
        try{
            return ResponseEntity.ok(menuService.updateDish(id,dish));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("dishes/{id}")
    public ResponseEntity getDish(@PathVariable Long id){
        try{
            return ResponseEntity.ok(menuService.getId(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @GetMapping("dishes/all")
    public ResponseEntity dishesAll(){
        try{
            return ResponseEntity.ok(menuService.allDishes());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }
}
