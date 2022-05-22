package com.server.demo.entities;



import com.server.demo.model.AchievementsForCreate;
import com.server.demo.model.MissionForCreate;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Optional;

@Entity
@DiscriminatorValue("goList")
public class GoListAchievementsEntity extends AchievementsEntity {
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersAchievementsRepository waitersAchievementsRepository, WaitersRepository waitersRepository, DishOrderRepository dishOrderRepository) {
        WaitersEntity waitersEntity=ordersEntity.getWaitersEntity();
        int goListAmount=0;
        Integer goListOrder=dishOrderRepository.goListOrder(ordersEntity.getId());
        if(goListOrder!=null){
            goListAmount=goListOrder.intValue();
        }
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
        waitersAchievementsEntity.setProgress(waitersAchievementsEntity.getProgress()+goListAmount);
        while((waitersAchievementsEntity.getProgress())>=this.getRequiredInitialAmount()+waitersAchievementsEntity.getLevel()*waitersAchievementsEntity.getLevel()*this.getLevelIncreaseFactor()) {
            waitersAchievementsEntity.setLevel(waitersAchievementsEntity.getLevel() + 1);
        }
        waitersRepository.save(waitersEntity);
        waitersAchievementsRepository.save(waitersAchievementsEntity);
    }

    public GoListAchievementsEntity(AchievementsForCreate achievementsForCreate){
        super(achievementsForCreate);
    }

    public GoListAchievementsEntity(){
    }
}
