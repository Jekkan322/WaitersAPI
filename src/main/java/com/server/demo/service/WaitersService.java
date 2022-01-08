package com.server.demo.service;

import com.server.demo.entities.*;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.*;
import com.server.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
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

    @Autowired
    private RatingRepository ratingRepository;

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
        Collection<WaitersAchievementsEntity> resultWaitersAchievements=waitersAchievementsRepository.filterWaiterByLevel(waiters.getId());
        return resultWaitersAchievements.stream().map(Achievements::toModel).collect(Collectors.toList());
    }

    public List<MissionForMobile> getAllMissions(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        Collection<WaitersMissionEntity> resultWaitersMissions= waitersMissionRepository.filterWaiterByMissions(waiters.getId());
        List<MissionForMobile> result= new ArrayList<>();
        for(MissionEntity missionEntity:missionRepository.findAll()){
            for(WaitersMissionEntity waitersMissionEntity:resultWaitersMissions){
                if(missionEntity.getId()==waitersMissionEntity.getMission().getId()){
                    result.add(MissionForMobile.toModel(missionEntity,waitersMissionEntity));
                }
            }
            if(!waitersMissionRepository.findByWaitersIdAndMissionId(waiters.getId(),missionEntity.getId()).isPresent()){
                result.add(MissionForMobile.toModel(missionEntity,new WaitersMissionEntity()));
            }
        }
        return result;
    }

    public List<WaitersForMobile> filter(Long id,String filter) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
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
        List<WaitersEntity> result=new ArrayList<>();
        for(WaitersEntity waitersEntity:waitersRepository.findAll()){
            waitersEntity.setRating(ratingRepository.filterAllRating(date,waitersEntity.getId()));
            result.add(waitersEntity);
        }
        return result.stream().map(WaitersForMobile::toModel).sorted((h1, h2) -> h2.getRating().compareTo(h1.getRating())).collect(Collectors.toList());
    }
}
