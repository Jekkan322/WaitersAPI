package com.server.demo.service;

import com.server.demo.entities.*;
import com.server.demo.exception.AchievementsTypeNotFoundException;
import com.server.demo.exception.MissionTypeNotFoundException;
import com.server.demo.model.*;
import com.server.demo.repositories.AchievementsRepository;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AchievementsService {
    @Autowired
    WaitersAchievementsRepository waitersAchievementsRepository;

    @Autowired
    AchievementsRepository achievementsRepository;

    @Autowired
    WaitersRepository waitersRepository;

    @Autowired
    DishOrderRepository dishOrderRepository;

    public void checkAllAchievements(OrdersEntity ordersEntity){
        for(AchievementsEntity achievementsEntity:achievementsRepository.findAll()) {
            achievementsEntity.processOrder(ordersEntity,waitersAchievementsRepository,waitersRepository,dishOrderRepository);
        }

    }

    public List<AchievementsForWeb> allAchievements(){
        List<AchievementsForWeb> result =new ArrayList<AchievementsForWeb>();
        for(AchievementsEntity achievementsEntity:achievementsRepository.findAll()){
            result.add(new AchievementsForWeb(achievementsEntity.getId(),achievementsEntity.getName(),achievementsEntity.getDescription(),achievementsEntity.getRequiredInitialAmount(),achievementsEntity.getLevelIncreaseFactor()));
        }
        return result;
    }

    public AchievementsForWeb updateAchievements(Long id, AchievementsForWeb achievements){
        AchievementsEntity achievementsEntity = achievementsRepository.findById(id).get();
        achievementsEntity.setName(achievements.getName());
        achievementsEntity.setDescription(achievements.getDescription());
        achievementsEntity.setRequiredInitialAmount(achievements.getRequiredInitialAmount());
        achievements.setLevelIncreaseFactor(achievements.getLevelIncreaseFactor());
        return AchievementsForWeb.toModel(achievementsRepository.save(achievementsEntity));
    }

    public Long deleteAchievements(Long id){
        AchievementsEntity achievementsEntity=achievementsRepository.findById(id).get();
        achievementsRepository.deleteById(achievementsEntity.getId());
        return id;
    }

    public AchievementsForCreate createAchievements(AchievementsForCreate achievementsForCreate) throws AchievementsTypeNotFoundException{
        AchievementsEntity achievementsEntity=null;
        switch(achievementsForCreate.getAchievementsType()){
            case "proceeds":
                achievementsEntity=new ProceedsAchievementsEntity(achievementsForCreate);
                break;
            case "goList":
                achievementsEntity=new GoListAchievementsEntity(achievementsForCreate);
                break;
            case "orderClosed":
                achievementsEntity=new OrdersClosedAchievementsEntity(achievementsForCreate);
                break;
            default:
                throw new AchievementsTypeNotFoundException("Данного типа миссии не существует");
        }
        return AchievementsForCreate.toModel(achievementsRepository.save(achievementsEntity));
    }



}
