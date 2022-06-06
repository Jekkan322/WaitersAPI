package com.server.demo.repositories;

import com.server.demo.entities.MissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MissionRepository extends CrudRepository<MissionEntity,Long> {
    /*@Query("select u from MissionEntity u WHERE TYPE(u)=ProceedsMissionEntity ")
    MissionEntity getMissionProceeds();*/

    @Query("select max(dateOfCreation) from MissionEntity")
    Date lastMission();
}
