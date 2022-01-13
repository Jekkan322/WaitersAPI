package com.server.demo.repositories;

import com.server.demo.entities.AchievementsEntity;
import com.server.demo.entities.WaitersAchievementsEntity;
import com.server.demo.entities.WaitersEntity;
import com.server.demo.model.Waiters;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface WaitersAchievementsRepository extends CrudRepository<WaitersAchievementsEntity,Long> {

    Optional<WaitersAchievementsEntity> findByWaitersAndAchievements(WaitersEntity waiters, AchievementsEntity achievements);
    Collection<WaitersAchievementsEntity> findByWaiters(WaitersEntity waiters);
    @Query("select u from WaitersAchievementsEntity u WHERE u.progress>0 and u.waiters.id=?1")
    Collection<WaitersAchievementsEntity> filterWaiterByLevel(Long waitersId);


}
