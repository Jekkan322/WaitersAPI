package com.server.demo.service;

import com.server.demo.entities.*;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.Achievements;
import com.server.demo.model.Mission;
import com.server.demo.model.MissionForMobile;
import com.server.demo.model.Waiters;
import com.server.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaitersService {
    @Autowired
    private WaitersRepository waitersRepository;

    @Autowired
    private AchievementsRepository achievementsRepository;

    @Autowired
    private WaitersAchievementsRepository waitersAchievementsRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private WaitersMissionRepository waitersMissionRepository;

    public List<Waiters> searchByName(String firstName,String lastName,String middleName){
        Collection<WaitersEntity> waiters =waitersRepository.findByFullName(lastName,firstName,middleName);
        return waiters.stream().map(Waiters::toModel).collect(Collectors.toList());
    }

    public List<Waiters> getAllWaiters(){
        Iterable<WaitersEntity> waiters= waitersRepository.findAll();
        List<WaitersEntity> resultEntity=new ArrayList<>();
        waiters.forEach(a->resultEntity.add(a));
        return resultEntity.stream().map(Waiters::toModel).collect(Collectors.toList());
    }

    public Waiters createWaiters(WaitersEntity waitersEntity){
        WaitersEntity waiter= waitersRepository.save(waitersEntity);
        return Waiters.toModel(waiter);
    }

    public Waiters updateWaiters(Long id,WaitersEntity waitersEntity) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        waiters.setFirstName(waitersEntity.getFirstName());
        waiters.setLastName(waitersEntity.getLastName());
        waiters.setMiddleName(waitersEntity.getMiddleName());
        waiters.setPosition(waitersEntity.getPosition());
        waiters.setRating(waitersEntity.getRating());
        waiters.setDateOfEntry(waitersEntity.getDateOfEntry());
        waiters.setWaitersAchievements(waitersEntity.getWaitersAchievements());
        return Waiters.toModel(waitersRepository.save(waiters));
    }

    public Long deleteWaiter(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        waitersRepository.deleteById(id);
        return id;
    }

    public List<Achievements> getAllAchievements(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        List<AchievementsEntity> resultAchievements=new ArrayList<>();
        List<WaitersAchievementsEntity> resultWaitersAchievements= new ArrayList<>();
        for(WaitersAchievementsEntity waitersAchievements:waitersAchievementsRepository.findAll()){
            if(waitersAchievements.getWaiters().getId()==waiters.getId() && waitersAchievements.getLevel()>0){
                resultAchievements.add(achievementsRepository.findById(waitersAchievements.getAchievements().getId()).get());
                resultWaitersAchievements.add(waitersAchievements);
            }
        }
        List<Achievements> result = new ArrayList<>();
        for(AchievementsEntity achievementsEntity:resultAchievements){
            for(WaitersAchievementsEntity waitersAchievementsEntity:resultWaitersAchievements){
                if(achievementsEntity.getId()==waitersAchievementsEntity.getAchievements().getId()){
                    result.add(Achievements.toModel(achievementsEntity,waitersAchievementsEntity));
                }
            }
        }
        return result;
    }

    public List<MissionForMobile> getAllMissions(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        List<MissionEntity> resultMissions=new ArrayList<>();
        List<WaitersMissionEntity> resultWaitersMissions= new ArrayList<>();
        for(WaitersMissionEntity waitersMission: waitersMissionRepository.findAll()){
            if(waitersMission.getWaiters().getId()==waiters.getId()){
                resultMissions.add(missionRepository.findById(waitersMission.getMission().getId()).get());
                resultWaitersMissions.add(waitersMission);
            }
        }
        List<MissionForMobile> result= new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            for(WaitersMissionEntity waitersMissionEntity:resultWaitersMissions){
                if(missionEntity.getId()==waitersMissionEntity.getMission().getId()){
                    result.add(MissionForMobile.toModel(missionEntity,waitersMissionEntity));
                }
            }
            if(waitersMissionRepository.findByMission(missionEntity).isPresent()){
                result.add(MissionForMobile.toModel(missionEntity,new WaitersMissionEntity()));
            }
        }
        return result;
    }
}
