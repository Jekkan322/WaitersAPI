package com.server.demo.service;

import com.server.demo.entities.AchievementsEntity;
import com.server.demo.entities.OrdersEntity;
import com.server.demo.entities.WaitersEntity;
import com.server.demo.repositories.AchievementsRepository;
import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementsService {
    @Autowired
    WaitersAchievementsRepository waitersAchievementsRepository;

    @Autowired
    AchievementsRepository achievementsRepository;

    @Autowired
    WaitersRepository waitersRepository;

    public void checkAllAchievements(OrdersEntity ordersEntity){
        for(AchievementsEntity achievementsEntity:achievementsRepository.findAll()) {
            achievementsEntity.processOrder(ordersEntity,waitersAchievementsRepository,waitersRepository);
        }

    }

}
