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
    private DishOrderRepository dishOrderRepository;

    @Autowired
    private WaitersAchievementsRepository waitersAchievementsRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private WaitersMissionRepository waitersMissionRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public List<Waiters> searchByName(String firstName,String lastName,String middleName){
        Collection<WaitersEntity> waiters =waitersRepository.findByFullName(lastName,firstName,middleName);
        return waiters.stream().map(Waiters::toModel).collect(Collectors.toList());
    }

    public Waiters getWaiter(Long id){
        WaitersEntity waitersEntity=waitersRepository.findById(id).get();
        return Waiters.toModel(waitersEntity);
    }

    public List<Waiters> getAllWaiters(){
        Iterable<WaitersEntity> waiters= waitersRepository.findAll();
        List<WaitersEntity> resultEntity=new ArrayList<>();
        for(WaitersEntity waitersEntity:waiters){
            if(waitersEntity.isActive()){
                resultEntity.add(waitersEntity);
            }
        }
        //waiters.forEach(a->resultEntity.add(a));
        return resultEntity.stream().map(Waiters::toModel).collect(Collectors.toList());
    }

    public Waiters createWaiters(Waiters waiters){
        if(waiters.getTotalScores()==null){
            waiters.setTotalScores(0l);
        }
        WaitersEntity waitersEntity=new WaitersEntity();
        waitersEntity.setRating(waiters.getTotalScores());
        waitersEntity.setPosition(waiters.getPosition());
        waitersEntity.setFirstName(waiters.getFirstName());
        waitersEntity.setLastName(waiters.getLastName());
        waitersEntity.setMiddleName(waiters.getMiddleName());
        waitersEntity.setDateOfEntry(waiters.getEmploymentDate());
        waitersRepository.save(waitersEntity);
        return Waiters.toModel(waitersEntity);
    }

    public Waiters updateWaiters(Long id,Waiters waiters) throws WaiterNotFoundException {
        WaitersEntity waitersEntity = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        waitersEntity.setFirstName(waiters.getFirstName());
        waitersEntity.setLastName(waiters.getLastName());
        waitersEntity.setMiddleName(waiters.getMiddleName());
        waitersEntity.setPosition(waiters.getPosition());
        waitersEntity.setRating(waiters.getTotalScores());
        waitersEntity.setDateOfEntry(waiters.getEmploymentDate());
        return Waiters.toModel(waitersRepository.save(waitersEntity));
    }

    public WaitersEntity deleteWaiter(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        //waitersRepository.deleteById(id);
        waiters.setActive(false);
        return waitersRepository.save(waiters);
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

    public List<WaitersForMobile> filter(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        /*Date dateNow= Date.from(ZonedDateTime.now().toInstant());
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
        Date date=calendar.getTime();*/
        List<WaitersEntity> result=new ArrayList<>();
        List<WaitersForMobile> resultModel=new ArrayList<>();
        Long resultFilter;
        for(WaitersEntity waitersEntity:waitersRepository.findAll()){
            resultFilter=ratingRepository.filterAllRating(waitersEntity.getId());
            if(resultFilter!=null){
                waitersEntity.setRating(ratingRepository.filterAllRating(waitersEntity.getId()));
            }else{
                waitersEntity.setRating(0l);
            }
            result.add(waitersEntity);
        }
        for(WaitersEntity waitersEntity:result){
            resultModel.add(WaitersForMobile.toModel(waitersEntity,waiters.getId()));
        }
        return resultModel.stream().sorted((h1, h2) -> h2.getScores().compareTo(h1.getScores())).collect(Collectors.toList());
    }

    public Statistics statistics(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        /*Date dateNow= Date.from(ZonedDateTime.now().toInstant());
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
        Date date=calendar.getTime();*/
        Statistics result=new Statistics();
        /*Integer orders=ordersRepository.countClosedOrders(waiters.getId());
        if(orders==null){
            result.setOrders(0);
        }
        else{
            result.setOrders(orders);
        }*/
        Integer average = ordersRepository.averageRevenue(waiters.getId());
        if(average==null){
            result.setAverageCheque(0);
        }else{
            result.setAverageCheque(average);
        }
        Long rating=ratingRepository.filterAllRating(waiters.getId());
        if(rating==null){
            result.setRating(0);
        }else{
            result.setRating(rating.intValue());
        }
        Integer revenue=ordersRepository.waiterRevenue(waiters.getId());
        if(revenue==null){
            result.setRevenue(0);
        }else{
            result.setRevenue(revenue);
        }
        if(dishOrderRepository.goListCount(waiters.getId())==null){
            result.setGoList(0);
        }else{
            result.setGoList(dishOrderRepository.goListCount(waiters.getId()));
        }
        return result;
    }

    public ArrayList<WaitersPlugin> waitersPlugins(ArrayList<WaitersPlugin> waitersPlugins){
        ArrayList<WaitersPlugin> waiters=new ArrayList<>();
        for(int i=0;i<waitersPlugins.size();i++){
            WaitersEntity waitersEntity=new WaitersEntity();
            if(!waitersRepository.findByUUID(waitersPlugins.get(i).getgUID()).isPresent()){
                waitersEntity.setFirstName(waitersPlugins.get(i).getFirstName());
                waitersEntity.setLastName(waitersPlugins.get(i).getLastName());
                waitersEntity.setUUID(waitersPlugins.get(i).getgUID());
                waitersEntity.setRating(0l);
                waitersRepository.save(waitersEntity);
                waiters.add(new WaitersPlugin(waitersEntity.getUUID(),waitersEntity.getFirstName(),waitersEntity.getLastName()));
            }
        }
        return waiters;
    }
}
