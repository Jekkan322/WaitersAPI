package com.server.demo.service;

import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.OrdersEntity;
import com.server.demo.entities.RatingEntity;
import com.server.demo.model.Mission;
import com.server.demo.model.MissionsOfRestaurant;
import com.server.demo.model.Orders;
import com.server.demo.repositories.MissionRepository;
import com.server.demo.repositories.RatingRepository;
import com.server.demo.repositories.WaitersMissionRepository;
import com.server.demo.repositories.WaitersRepository;
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

    public void checkAllMissions(OrdersEntity ordersEntity,RatingEntity ratingEntity){
        for(MissionEntity missionEntity:missionRepository.findAll()){
            missionEntity.processOrder(ordersEntity,waitersMissionRepository,waitersRepository,ratingEntity,ratingRepository);
        }
    }

    public Mission createMission(MissionEntity missionEntity){
        if(missionEntity.getDateOfCreation()==null){
            missionEntity.setDateOfCreation(Date.from(ZonedDateTime.now().toInstant()));
        }
        return Mission.toModel(missionRepository.save(missionEntity));
    }

    public List<MissionsOfRestaurant> allMissions(){
        List<MissionsOfRestaurant> result =new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            Integer progress= waitersMissionRepository.allProgress(missionEntity.getId());
            if(progress==null){
                result.add(new MissionsOfRestaurant(missionEntity.getMissionName(),0,missionEntity.getDeadlineTime()));
            }
            else {
                result.add(new MissionsOfRestaurant(missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime()));
            }
        }
        return result;
    }





}
