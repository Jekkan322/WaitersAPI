package com.server.demo.entities;

import com.server.demo.model.MissionForCreate;
import com.server.demo.repositories.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Entity
@DiscriminatorValue("orderClosed")
public class OrdersClosedMissionEntity extends MissionEntity{
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository, WaitersRepository waitersRepository, RatingRepository ratingRepository, DishOrderRepository dishOrderRepository) {
        WaitersEntity waitersEntity=ordersEntity.getWaitersEntity();
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
            waitersMissionEntity.setProgress(waitersMissionEntity.getProgress()+1);
        }
        if(oldProgress>=this.getPersonalMissionAmount()){
            RatingEntity ratingEntity=new RatingEntity();
            waitersEntity.setRating(waitersEntity.getRating()+Math.round(this.getAmountReward()/(1.0*this.getRequirementsAmount()/1)));
            ratingEntity.setRating(Math.round(this.getAmountReward()/(1.0*this.getRequirementsAmount()/1)));
            ratingEntity.setWaitersEntity(waitersEntity);
            ratingEntity.setTimeOfReceipt(Date.from(ZonedDateTime.now().toInstant()));
            ratingRepository.save(ratingEntity);
        }else{
            if(this.getPersonalMissionAmount()<=waitersMissionEntity.getProgress()){
                RatingEntity ratingEntity=new RatingEntity();
                waitersEntity.setRating(waitersEntity.getRating()+Math.round(this.getAmountReward()/(1.0*this.getRequirementsAmount().intValue()/this.getPersonalMissionAmount())));
                ratingEntity.setRating(Math.round(this.getAmountReward()/(1.0*this.getRequirementsAmount().intValue()/this.getPersonalMissionAmount())));
                ratingEntity.setWaitersEntity(waitersEntity);
                ratingEntity.setTimeOfReceipt(Date.from(ZonedDateTime.now().toInstant()));
                ratingRepository.save(ratingEntity);
            }
        }
        waitersRepository.save(waitersEntity);
        waitersMissionRepository.save(waitersMissionEntity);

    }

    @Override
    public Integer calcProgress(OrdersRepository ordersRepository, DishOrderRepository dishOrderRepository) {
        Integer result=ordersRepository.sumOrdersClosed();
        if(result==null){
            result=0;
        }
        return result;
    }

    @Override
    public String getType() {
        return "orderClosed";
    }


    public OrdersClosedMissionEntity(MissionForCreate missionForCreate){
        super(missionForCreate);
    }

    public OrdersClosedMissionEntity(){

    }
}
