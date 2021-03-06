package com.server.demo.service;

import com.server.demo.entities.MenuEntity;
import com.server.demo.entities.MissionEntity;
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
                menuEntity.setGoList(false);
                menuEntity.setActive(true);
                menuRepository.save(menuEntity);
                dishes.add(new Dish(menuEntity.getId().intValue(),menuEntity.getDishName(),menuEntity.getPrice(), menuEntity.isGoList()));
            }
        }
        return dishes;
    }

    public Dish updateDish(Long id){
        MenuEntity menuEntity=menuRepository.findById(id).get();
        if(menuEntity.isGoList()){
            menuEntity.setGoList(false);
        }else{
            menuEntity.setGoList(true);
        }
        menuRepository.save(menuEntity);
        return new Dish(menuEntity.getId().intValue(),menuEntity.getDishName(),menuEntity.getPrice(),menuEntity.isGoList());
    }

    public Dish getId(Long id){
        MenuEntity menuEntity=menuRepository.findById(id).get();
        return new Dish(menuEntity.getId().intValue(),menuEntity.getDishName(),menuEntity.getPrice(),menuEntity.isGoList());
    }

    public List<Dish> allDishes(){
        Iterable<MenuEntity> menuEntities= menuRepository.findAll();
        List<Dish> result=new ArrayList<>();
        for(MenuEntity menu:menuEntities){
            if(menu.isActive()){
                result.add(new Dish(menu.getId().intValue(),menu.getDishName(),menu.getPrice(),menu.isGoList()));
            }
        }
        //menuEntities.forEach(a->resultEntity.add(a));
        return result;
    }
}
