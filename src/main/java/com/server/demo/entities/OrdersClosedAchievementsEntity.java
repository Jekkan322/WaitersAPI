package com.server.demo.entities;

import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Optional;

@Entity
@DiscriminatorValue("orderClosed")
public class OrdersClosedAchievementsEntity extends AchievementsEntity{
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersAchievementsRepository waitersAchievementsRepository, WaitersRepository waitersRepository, DishOrderRepository dishOrderRepository) {
        WaitersEntity waitersEntity=ordersEntity.getWaitersEntity();
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
        waitersAchievementsEntity.setProgress(waitersAchievementsEntity.getProgress()+1);
        while((waitersAchievementsEntity.getProgress())>=this.getRequiredInitialAmount()+waitersAchievementsEntity.getLevel()*waitersAchievementsEntity.getLevel()*this.getLevelIncreaseFactor()) {
            waitersAchievementsEntity.setLevel(waitersAchievementsEntity.getLevel() + 1);
        }
        waitersRepository.save(waitersEntity);
        waitersAchievementsRepository.save(waitersAchievementsEntity);
    }
}
