package com.server.demo.repositories;

import com.server.demo.entities.RatingEntity;
import com.server.demo.entities.WaitersAchievementsEntity;
import com.server.demo.entities.WaitersEntity;
import com.server.demo.entities.WaitersMissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends CrudRepository<RatingEntity,Long> {

    @Query("select sum(rating) from RatingEntity where timeOfReceipt>?1\n" +
            "group by waitersEntity\n" +
            "having waitersEntity.id =?2")
    Long filterAllRating(Date date,Long waiterId);
}
