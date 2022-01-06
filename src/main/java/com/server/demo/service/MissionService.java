package com.server.demo.service;

import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.OrdersEntity;
import com.server.demo.model.Mission;
import com.server.demo.model.Orders;
import com.server.demo.repositories.MissionRepository;
import com.server.demo.repositories.WaitersMissionRepository;
import com.server.demo.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    @Autowired
    MissionRepository missionRepository;

    @Autowired
    WaitersMissionRepository waitersMissionRepository;

    @Autowired
    WaitersRepository waitersRepository;

    public void checkAllMissions(OrdersEntity ordersEntity){
        for(MissionEntity missionEntity:missionRepository.findAll()){
            missionEntity.processOrder(ordersEntity,waitersMissionRepository,waitersRepository);
        }
    }

    public Mission createMission(MissionEntity missionEntity){
        return Mission.toModel(missionRepository.save(missionEntity));
    }





}
