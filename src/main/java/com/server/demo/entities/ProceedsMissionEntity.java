package com.server.demo.entities;

import com.server.demo.repositories.WaitersMissionRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Entity
@DiscriminatorValue("proceeds")
public class ProceedsMissionEntity extends MissionEntity{
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository, WaitersRepository waitersRepository) {
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
        if(this.getDeadlineTime().after(Date.from(ZonedDateTime.now().toInstant()))){
            waitersMissionEntity.setProgress(waitersMissionEntity.getProgress()+ordersEntity.getOrderPrice());
        }
        WaitersEntity waitersEntity;
        Optional<WaitersEntity> optionalWaitersEntity=waitersMissionRepository.findByWaiters(ordersEntity.getWaitersEntity());
        if(optionalWaitersEntity.isPresent()){
            waitersEntity=optionalWaitersEntity.get();
        }
        else{
            waitersEntity=waitersRepository.findById(ordersEntity.getWaitersEntity().getId()).get();
        }
        if(this.getRequirementsForTheFirstAward()<=ordersEntity.getOrderPrice()){
            waitersEntity.setRating(waitersEntity.getRating()+(this.getAmountReward()/(this.getRequirementsAmount()/this.getRequirementsForTheFirstAward())));
        }
        if(waitersMissionEntity.getProgress()>this.getRequirementsForTheFirstAward()){
            waitersEntity.setRating(waitersEntity.getRating()+(this.getAmountReward()/((this.getRequirementsAmount()/this.getRequirementsForTheFirstAward())-waitersMissionEntity.getProgress())));
        }
        waitersRepository.save(waitersEntity);
        waitersMissionRepository.save(waitersMissionEntity);
    }
}
