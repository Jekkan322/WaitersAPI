package com.server.demo.service;

import com.server.demo.entities.MenuEntity;
import com.server.demo.entities.WaitersEntity;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.Dish;
import com.server.demo.model.Waiters;
import com.server.demo.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public ArrayList<Dish> findDishes(ArrayList<Dish> dish){
        ArrayList<Dish> dishes=new ArrayList<>();
        for(int i=0;i<dish.size();i++){
            MenuEntity menuEntity=new MenuEntity();
            if(!menuRepository.findById((long)dish.get(i).getId()).isPresent()){
                menuEntity.setId((long)dish.get(i).getId());
                menuEntity.setDishName(dish.get(i).getName());
                menuEntity.setPrice(dish.get(i).getPrice());
                menuEntity.setGoList(dish.get(i).isGoList());
                menuRepository.save(menuEntity);
                dishes.add(new Dish(menuEntity.getId().intValue(),menuEntity.getDishName(),menuEntity.getPrice(), menuEntity.isGoList()));
            }
        }
        return dishes;
    }

    public MenuEntity updateDish(Long id, Dish dish){
        MenuEntity menuEntity=menuRepository.findById(id).get();
        menuEntity.setPrice(dish.getPrice());
        menuEntity.setDishName(dish.getName());
        menuEntity.setGoList(dish.isGoList());
        return menuRepository.save(menuEntity);
    }

    public MenuEntity getId(Long id){
        MenuEntity menuEntity=menuRepository.findById(id).get();
        return menuEntity;
    }

    public List<MenuEntity> allDishes(){
        Iterable<MenuEntity> menuEntities= menuRepository.findAll();
        List<MenuEntity> resultEntity=new ArrayList<>();
        menuEntities.forEach(a->resultEntity.add(a));
        return resultEntity;
    }
}
