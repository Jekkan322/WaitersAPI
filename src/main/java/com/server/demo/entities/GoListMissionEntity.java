package com.server.demo.entities;

import com.server.demo.repositories.RatingRepository;
import com.server.demo.repositories.WaitersMissionRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("goList")
public class GoListMissionEntity extends MissionEntity{
    @Override
    public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository, WaitersRepository waitersRepository,RatingEntity ratingEntity, RatingRepository ratingRepository) {

    }
}
