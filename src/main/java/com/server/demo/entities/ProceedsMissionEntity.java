package com.server.demo.entities;

import com.server.demo.model.MissionForCreate;
import com.server.demo.repositories.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Entity
@DiscriminatorValue("proceeds")
public class ProceedsMissionEntity extends MissionEntity{
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository, WaitersRepository waitersRepository, RatingRepository ratingRepository, DishOrderRepository dishOrderRepository) {
        WaitersMissionEntity waitersMissionEntity;
        Optional<WaitersMissionEntity> optionalWaitersMissionEntity=waitersMissionRepository.findByWaitersAndMission(ordersEntity.getWaitersEntity(),this);
        if(optionalWaitersMissionEntity.isPresent()){
            waitersMissionEntity=optionalWaitersMissionEntity.get();
        }
        else{
            waitersMissionEntity=new WaitersMissionEntity();
            waitersMissionEntity.setId(new WaitersMissionKey(ordersEntity.getWaitersEntity().getId(),this.getId()));
            waitersMissionEntity.setWaiters(ordersEntity.getWaitersEntity());
            waitersMissionEntity.setMission(this);
        }
        int oldProgress=waitersMissionEntity.getProgress();
        if(this.getDeadlineTime().after(Date.from(ZonedDateTime.now().toInstant()))){
            waitersMissionEntity.setProgress(waitersMissionEntity.getProgress()+ordersEntity.getOrderPrice());

        }
        WaitersEntity waitersEntity;
        Optional<WaitersEntity> optionalWaitersEntity=waitersRepository.findById(ordersEntity.getWaitersEntity().getId());
        if(optionalWaitersEntity.isPresent()){
            waitersEntity=optionalWaitersEntity.get();
        }
        else{
            waitersEntity=waitersRepository.findById(ordersEntity.getWaitersEntity().getId()).get();
        }
        if(oldProgress>=this.getRequirementsForTheFirstAward()){
            RatingEntity ratingEntity=new RatingEntity();
            waitersEntity.setRating(waitersEntity.getRating()+Math.round(1.0*this.getAmountReward()/(1.0*this.getRequirementsAmount()/ordersEntity.getOrderPrice())));
            ratingEntity.setRating(Math.round(1.0*this.getAmountReward()/(1.0*this.getRequirementsAmount()/ordersEntity.getOrderPrice())));
            ratingEntity.setWaitersEntity(waitersEntity);
            ratingEntity.setTimeOfReceipt(Date.from(ZonedDateTime.now().toInstant()));
            ratingRepository.save(ratingEntity);
        }else{
            if(this.getRequirementsForTheFirstAward()<=waitersMissionEntity.getProgress()){
                RatingEntity ratingEntity=new RatingEntity();
                waitersEntity.setRating(waitersEntity.getRating()+Math.round(1.0*this.getAmountReward()/(1.0*this.getRequirementsAmount()/waitersMissionEntity.getProgress())));
                ratingEntity.setRating(Math.round(1.0*this.getAmountReward()/(1.0*this.getRequirementsAmount()/waitersMissionEntity.getProgress())));
                ratingEntity.setWaitersEntity(waitersEntity);
                ratingEntity.setTimeOfReceipt(Date.from(ZonedDateTime.now().toInstant()));
                ratingRepository.save(ratingEntity);
            }
        }

        waitersRepository.save(waitersEntity);
        waitersMissionRepository.save(waitersMissionEntity);
    }

    @Override
    public Integer calcProgress(Date date, OrdersRepository ordersRepository, DishOrderRepository dishOrderRepository) {
        Integer result=ordersRepository.sumRevenue(date);
        if(result==null){
            result=0;
        }
        return result;
    }

    public ProceedsMissionEntity(MissionForCreate missionForCreate){
        super(missionForCreate);
    }

    public ProceedsMissionEntity(){

    }
}
