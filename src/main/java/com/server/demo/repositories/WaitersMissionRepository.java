package com.server.demo.repositories;

import com.server.demo.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface WaitersMissionRepository extends CrudRepository<WaitersMissionEntity,Long> {

    Optional<WaitersMissionEntity> findByWaitersAndMission(WaitersEntity waiters, MissionEntity missionEntity);
    Optional<WaitersMissionEntity> findByMissionId(Long missionId);
    Optional<WaitersMissionEntity> findByWaitersId(Long waitersId);
    Optional<WaitersMissionEntity> findByWaitersIdAndMissionId(Long waitersId, Long missionId);

    @Query("select u from WaitersMissionEntity u WHERE u.waiters.id=?1")
    Collection<WaitersMissionEntity> filterWaiterByMissions(Long waitersId);

    @Query("select sum(progress) from WaitersMissionEntity where mission.id=?1")
    Integer allProgress(Long missionId);
}
