package com.server.demo.entities;

import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Optional;

@Entity
@DiscriminatorValue("proceeds")
public class ProceedsAchievementsEntity extends  AchievementsEntity{

    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersAchievementsRepository waitersAchievementsRepository, WaitersRepository waitersRepository) {
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
        int oldprogress=waitersAchievementsEntity.getProgress();
        waitersAchievementsEntity.setProgress(waitersAchievementsEntity.getProgress()+ordersEntity.getOrderPrice());
        WaitersEntity waitersEntity;
        Optional<WaitersEntity> optionalWaitersEntity=waitersRepository.findById(ordersEntity.getWaitersEntity().getId());
        if(optionalWaitersEntity.isPresent()){
            waitersEntity=optionalWaitersEntity.get();
        }
        else{
            waitersEntity=waitersRepository.findById(ordersEntity.getWaitersEntity().getId()).get();
        }
        int oldLevel=waitersAchievementsEntity.getLevel();
        if(waitersAchievementsEntity.getProgress()>=this.getRequiredInitialAmount()+waitersAchievementsEntity.getLevel()*this.getIncreasingAmountWithNewLevel()){
            for(int i=oldLevel;(waitersAchievementsEntity.getProgress()-oldprogress)>this.getRequiredInitialAmount()+waitersAchievementsEntity.getLevel()*this.getIncreasingAmountWithNewLevel();i++){
                waitersAchievementsEntity.setLevel(waitersAchievementsEntity.getLevel()+1);
                if(waitersAchievementsEntity.getLevel()==1){
                    waitersEntity.setRating(waitersEntity.getRating()+this.getInitialReward());
                }
                else if(waitersAchievementsEntity.getLevel()>1){
                        waitersEntity.setRating(waitersEntity.getRating()+this.getIncreasingRewardWithNewLevel());
                }
            }

        }
        waitersRepository.save(waitersEntity);
        waitersAchievementsRepository.save(waitersAchievementsEntity);

    }
}
