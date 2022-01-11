package com.server.demo.service;

import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.OrdersEntity;
import com.server.demo.model.Mission;
import com.server.demo.model.Restaurant;
import com.server.demo.model.MissionsOfRestaurant;
import com.server.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MissionService {
    @Autowired
    MissionRepository missionRepository;

    @Autowired
    WaitersMissionRepository waitersMissionRepository;

    @Autowired
    WaitersRepository waitersRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    DishOrderRepository dishOrderRepository;

    public void checkAllMissions(OrdersEntity ordersEntity){
        for(MissionEntity missionEntity:missionRepository.findAll()){
            missionEntity.processOrder(ordersEntity,waitersMissionRepository,waitersRepository,ratingRepository,dishOrderRepository);
        }
    }

    public Mission createMission(MissionEntity missionEntity){
        if(missionEntity.getDateOfCreation()==null){
            missionEntity.setDateOfCreation(Date.from(ZonedDateTime.now().toInstant()));
        }
        return Mission.toModel(missionRepository.save(missionEntity));
    }

    public List<Restaurant> allMissions(){
        List<Restaurant> result =new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            Integer progress= waitersMissionRepository.allProgress(missionEntity.getId());
            if(progress==null){
                result.add(new Restaurant("Чужая компания",new MissionsOfRestaurant(missionEntity.getMissionName(),0,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue())));
            }
            else {
                result.add(new Restaurant("Чужая компания",new MissionsOfRestaurant(missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue())));
            }
        }
        return result;
    }





}
