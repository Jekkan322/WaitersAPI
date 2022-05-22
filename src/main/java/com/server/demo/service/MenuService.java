package com.server.demo.service;

import com.server.demo.entities.MenuEntity;
import com.server.demo.model.Dish;
import com.server.demo.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
                menuRepository.save(menuEntity);
                dishes.add(new Dish(menuEntity.getId().intValue(),menuEntity.getDishName(),menuEntity.getPrice()));
            }
        }
        return dishes;
    }

}
