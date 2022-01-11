package com.server.demo.service;

import com.server.demo.entities.*;
import com.server.demo.exception.MissionTypeNotFoundException;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.*;
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

    public Mission createMission(MissionForCreate missionForCreate) throws MissionTypeNotFoundException {
        if(missionForCreate.getDateOfCreation()==null){
            missionForCreate.setDateOfCreation(Date.from(ZonedDateTime.now().toInstant()));
        }
        MissionEntity missionEntity=null;
        switch(missionForCreate.getMissionType()){
            case "proceeds":
                missionEntity=new ProceedsMissionEntity(missionForCreate);
                break;
            case "goList":
                missionEntity=new GoListMissionEntity(missionForCreate);
                break;
            default:
                throw new MissionTypeNotFoundException("Данного типа миссии не существует");
        }
        return Mission.toModel(missionRepository.save(missionEntity));
    }

    public List<MissionsOfRestaurant> getAllMission(){
        List<MissionsOfRestaurant> statistics=new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            Integer progress= waitersMissionRepository.allProgress(missionEntity.getId());
            if(progress==null){
                statistics.add(new MissionsOfRestaurant(missionEntity.getMissionName(),0,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
            else {
                statistics.add(new MissionsOfRestaurant(missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
        }
        return statistics;
    }

    public Mission updateWaiters(Mission mission){
        MissionEntity missionEntity = missionRepository.findById(mission.getId()).get();
        missionEntity.setMissionName(mission.getMissionName());
        missionEntity.setMissionDescription(mission.getMissionDescription());
        missionEntity.setAmountReward(mission.getAmountReward());
        missionEntity.setDeadlineTime(mission.getDeadlineTime());
        missionEntity.setRequirementsAmount(mission.getAmountReward());
        missionEntity.setRequirementsForTheFirstAward(mission.getRequirementsForTheFirstAward());
        return Mission.toModel(missionRepository.save(missionEntity));
    }

    public Long deleteMission(Long id){
        MissionEntity missionEntity=missionRepository.findById(id).get();
        missionRepository.deleteById(missionEntity.getId());
        return id;
    }

    public Restaurant allMissions(){
        Restaurant result =new Restaurant();
        List<MissionsOfRestaurant> statistics=new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            Integer progress= waitersMissionRepository.allProgress(missionEntity.getId());
            if(progress==null){
                statistics.add(new MissionsOfRestaurant(missionEntity.getMissionName(),0,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
            else {
                statistics.add(new MissionsOfRestaurant(missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
        }
        result.setName("Чужая компания");
        result.setStatistics(statistics);
        return result;
    }





}
