package com.server.demo.entities;

import com.server.demo.repositories.WaitersAchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@DiscriminatorValue("proceeds")
public class ProceedsEntity extends  AchievementsEntity{

    @Override
    public void processOrder(OrdersEntity ordersEntity,WaitersAchievementsRepository waitersAchievementsRepository) {
        WaitersAchievementsEntity waitersAchievementsEntity;
        Optional<WaitersAchievementsEntity> optionalWaitersAchievementsEntity=waitersAchievementsRepository.findByWaitersAndAchievements(ordersEntity.getWaitersEntity(),this);
        if(optionalWaitersAchievementsEntity.isPresent()){
            waitersAchievementsEntity=optionalWaitersAchievementsEntity.get();
        }
        else{
            waitersAchievementsEntity=new WaitersAchievementsEntity();
            waitersAchievementsEntity.setId(new WaitersAchievementsKey(ordersEntity.getWaitersEntity().getId(),this.getId()));
            waitersAchievementsEntity.setWaiters(ordersEntity.getWaitersEntity());
            waitersAchievementsEntity.setAchievements(this);
        }
        waitersAchievementsEntity.setProgress(waitersAchievementsEntity.getProgress()+ordersEntity.getOrderPrice());
        if(waitersAchievementsEntity.getProgress()>=this.getRequiredInitialAmount()+waitersAchievementsEntity.getLevel()*this.getIncreasingAmountWithNewLevel()){
            waitersAchievementsEntity.setLevel(waitersAchievementsEntity.getLevel()+1);
        }
        waitersAchievementsRepository.save(waitersAchievementsEntity);
    }
}
