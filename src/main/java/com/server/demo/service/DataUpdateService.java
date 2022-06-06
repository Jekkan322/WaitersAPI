package com.server.demo.service;

import com.server.demo.entities.WaitersEntity;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.DataUpdate;
import com.server.demo.model.DateForUpdate;
import com.server.demo.repositories.MissionRepository;
import com.server.demo.repositories.OrdersRepository;
import com.server.demo.repositories.RatingRepository;
import com.server.demo.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DataUpdateService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    WaitersRepository waitersRepository;

    @Autowired
    MissionRepository missionRepository;

    public DataUpdate polling(Long waitersId, DateForUpdate date) throws WaiterNotFoundException {
        WaitersEntity waiters=waitersRepository.findById(waitersId).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        DataUpdate result = new DataUpdate();
        Date lastOrderWaiter = ordersRepository.lastOrderWaiter(waiters.getId());
        if(date.getPersonal().after(lastOrderWaiter)){
            result.setPersonal(false);
        }else{
            result.setPersonal(true);
        }
        Date lastOrder=ordersRepository.lastOrder();
        if(date.getRestaurant().after(lastOrder)){
            result.setRestaurant(false);
        }else{
            result.setRestaurant(true);
        }
        Date lastRating=ratingRepository.lastRating();
        if(date.getLeaderboard().after(lastRating)){
            result.setLeaderboard(false);
        }else{
            result.setLeaderboard(true);
        }
        Date missions=missionRepository.lastMission();
        if(date.getLeaderboard().after(missions)){
            result.setLeaderboard(false);
        }else{
            result.setLeaderboard(true);
        }
        return result;
    }

}
