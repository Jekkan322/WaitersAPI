package com.server.demo.repositories;

import com.server.demo.entities.MissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends CrudRepository<MissionEntity,Long> {
    /*@Query("select u from MissionEntity u WHERE TYPE(u)=ProceedsMissionEntity ")
    MissionEntity getMissionProceeds();*/

}
