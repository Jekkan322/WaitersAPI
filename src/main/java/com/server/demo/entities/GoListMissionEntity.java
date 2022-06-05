package com.server.demo.entities;

import com.server.demo.model.MissionForCreate;
import com.server.demo.repositories.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Entity
@DiscriminatorValue("goList")
public class GoListMissionEntity extends MissionEntity{
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository, WaitersRepository waitersRepository, RatingRepository ratingRepository, DishOrderRepository dishOrderRepository) {
        WaitersEntity waitersEntity=ordersEntity.getWaitersEntity();
        int goListAmount=0;
        Integer goListOrder=dishOrderRepository.goListOrder(ordersEntity.getId());
        if(goListOrder!=null){
            goListAmount=goListOrder.intValue();
        }
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
            waitersMissionEntity.setProgress(waitersMissionEntity.getProgress()+goListAmount);
        }
        if(oldProgress>=this.getPersonalMissionAmount()){
            RatingEntity ratingEntity=new RatingEntity();
            waitersEntity.setRating(waitersEntity.getRating()+Math.round(this.getAmountReward()/(1.0*this.getRequirementsAmount()/goListAmount)));
            ratingEntity.setRating(Math.round(1.0*this.getAmountReward()/(1.0*this.getRequirementsAmount()/goListAmount)));
            ratingEntity.setWaitersEntity(waitersEntity);
            ratingEntity.setTimeOfReceipt(Date.from(ZonedDateTime.now().toInstant()));
            ratingRepository.save(ratingEntity);
        }else{
            if(this.getPersonalMissionAmount()<=waitersMissionEntity.getProgress()){
                RatingEntity ratingEntity=new RatingEntity();
                waitersEntity.setRating(waitersEntity.getRating()+Math.round(this.getAmountReward()/(1.0*this.getRequirementsAmount().intValue()/waitersMissionEntity.getProgress())));
                ratingEntity.setRating(Math.round(1.0*this.getAmountReward()/(1.0*this.getRequirementsAmount().intValue()/waitersMissionEntity.getProgress())));
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
        Integer result=dishOrderRepository.sumGoList(date);
        if(result==null){
            result=0;
        }
        return dishOrderRepository.sumGoList(date);
    }

    @Override
    public String getType() {
        return "goList";
    }

    public GoListMissionEntity(MissionForCreate missionForCreate){
        super(missionForCreate);
    }

    public GoListMissionEntity(){

    }
}
