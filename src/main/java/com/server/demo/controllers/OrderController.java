package com.server.demo.controllers;


import com.server.demo.entities.OrdersEntity;
import com.server.demo.exception.OrderNotFoundException;
import com.server.demo.model.Orders;
import com.server.demo.model.OrdersForCreate;
import com.server.demo.repositories.OrdersRepository;
import com.server.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("orders/all")
    public ResponseEntity getAll(){
        try{
            return  ResponseEntity.ok(ordersService.ordersGetAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

    @PostMapping("orders/create")
    public ResponseEntity createOrder(@RequestBody OrdersForCreate orders){
        try{
            return ResponseEntity.ok(ordersService.ordersCreate(orders));
        }catch (Exception e){
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter( writer );
            e.printStackTrace( printWriter );
            printWriter.flush();
            String stackTrace = writer.toString();
            return ResponseEntity.badRequest().body("Произошла ошибка: "+e.getMessage()+"\n"+stackTrace);
        }
    }

    @PutMapping("orders/completed/{id}")
    public ResponseEntity completedOrder(@PathVariable Long id){
        try{
            return ResponseEntity.ok(ordersService.ordersCompleted(id));
        }catch (OrderNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }
}
