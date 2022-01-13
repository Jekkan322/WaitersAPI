package com.server.demo.repositories;

import com.server.demo.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaitersMissionRepository extends CrudRepository<WaitersMissionEntity,Long> {

    Optional<WaitersMissionEntity> findByWaitersAndMission(WaitersEntity waiters, MissionEntity missionEntity);
    Optional<List<WaitersMissionEntity>> findByMissionId(Long missionId);
    Optional<List<WaitersMissionEntity>> findByWaitersId(Long waitersId);
    Optional<WaitersMissionEntity> findByWaitersIdAndMissionId(Long waitersId, Long missionId);

    @Query("select u from WaitersMissionEntity u WHERE u.waiters.id=?1")
    Collection<WaitersMissionEntity> filterWaiterByMissions(Long waitersId);

    @Query("select sum(progress) from WaitersMissionEntity where mission.id=?1")
    Integer allProgress(Long missionId);

    /*@Query("select  sum(progress) from WaitersMissionEntity Where waiters.id=?2 and dateProgress>?1 and mission.missionName='блюдо дня'")
    Integer goListProgress(Date date, Long waitersId);*/
}
