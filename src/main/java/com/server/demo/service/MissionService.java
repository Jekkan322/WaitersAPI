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
import java.util.Calendar;
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

    @Autowired
    OrdersRepository ordersRepository;

    public void checkAllMissions(OrdersEntity ordersEntity){
        for(MissionEntity missionEntity:missionRepository.findAll()){
            missionEntity.processOrder(ordersEntity,waitersMissionRepository,waitersRepository,ratingRepository,dishOrderRepository);
        }
    }

    public Mission getMission(Long id){
        MissionEntity missionEntity=missionRepository.findById(id).get();
        return Mission.toModel(missionEntity);
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
            case "orderClosed":
                missionEntity=new OrdersClosedMissionEntity(missionForCreate);
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
                statistics.add(new MissionsOfRestaurant(missionEntity.getId(),missionEntity.getMissionName(),0,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
            else {
                statistics.add(new MissionsOfRestaurant(missionEntity.getId(),missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
        }
        return statistics;
    }

    public Mission updateWaiters(Long id,Mission mission){
        MissionEntity missionEntity = missionRepository.findById(id).get();
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
        /*List<WaitersMissionEntity> waitersMissionEntity=waitersMissionRepository.findByMissionId(id).get();
        for(WaitersMissionEntity waitersMission:waitersMissionEntity){
            waitersMissionRepository.delete(waitersMission);
        }*/
        missionRepository.deleteById(missionEntity.getId());
        return id;
    }

    public Restaurant allMissions(){
        Restaurant result =new Restaurant();
        List<MissionsOfRestaurant> statistics=new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            Integer progress= waitersMissionRepository.allProgress(missionEntity.getId());
            if(progress==null){
                statistics.add(new MissionsOfRestaurant(missionEntity.getId(),missionEntity.getMissionName(),0,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
            else {
                statistics.add(new MissionsOfRestaurant(missionEntity.getId(),missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
            }
        }
        result.setName("Palo-alto restaurant");
        result.setStatistics(statistics);
        return result;
    }

    public StatisticsForWeb statisticsWeb(String filter) throws MissionTypeNotFoundException {
        Date dateNow= Date.from(ZonedDateTime.now().toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        switch (filter){
            case("day"):
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                break;
            case("week"):
                calendar.add(Calendar.WEEK_OF_MONTH,-1);
                break;
            case("month"):
                calendar.add(Calendar.MONTH,-1);
                break;
        }
        Date date=calendar.getTime();
        List<Statistics> statistics=new ArrayList<>();
        List<MissionsOfRestaurant> missions=new ArrayList<>();
        for(WaitersEntity waiters:waitersRepository.findAll()){
            Statistics result=new Statistics();
            Integer orders=ordersRepository.countClosedOrders(date,waiters.getId());
            if(orders==null){
                result.setOrders(0);
            }
            else{
                result.setOrders(orders);
            }
            Long rating=ratingRepository.filterAllRating(date,waiters.getId());
            if(rating==null){
                result.setRating(0);
            }else{
                result.setRating(rating.intValue());
            }
            Integer revenue=ordersRepository.waiterRevenue(date,waiters.getId());
            if(revenue==null){
                result.setRevenue(0);
            }else{
                result.setRevenue(revenue);
            }
            if(dishOrderRepository.goListCount(date,waiters.getId())==null){
                result.setGoList(0);
            }else{
                result.setGoList(dishOrderRepository.goListCount(date,waiters.getId()));
            }
            result.setFirstName(waiters.getFirstName());
            result.setLastName(waiters.getLastName());
            statistics.add(result);

        }
        int progress=0;
        for(MissionEntity missionEntity:missionRepository.findAll()){
            progress=missionEntity.calcProgress(date,ordersRepository,dishOrderRepository);
            missions.add(new MissionsOfRestaurant(missionEntity.getId(),missionEntity.getMissionName(),progress,missionEntity.getDeadlineTime(),missionEntity.getRequirementsAmount().intValue()));
        }
        return new StatisticsForWeb(statistics,missions);


    }





}
