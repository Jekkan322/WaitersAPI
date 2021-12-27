package com.server.demo.service;

import com.server.demo.entities.WaitersEntity;
import com.server.demo.exception.WaiterNotFoundException;
import com.server.demo.model.Waiters;
import com.server.demo.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaitersService {
    @Autowired
    private WaitersRepository waitersRepository;

    public List<Waiters> searchByName(String firstName,String lastName,String middleName){
        Collection<WaitersEntity> waiters =waitersRepository.findByFullName(lastName,firstName,middleName);
        return waiters.stream().map(Waiters::toModel).collect(Collectors.toList());
    }

    public List<Waiters> getAllWaiters(){
        Iterable<WaitersEntity> waiters= waitersRepository.findAll();
        List<WaitersEntity> resultEntity=new ArrayList<>();
        waiters.forEach(a->resultEntity.add(a));
        return resultEntity.stream().map(Waiters::toModel).collect(Collectors.toList());
    }

    public Waiters createWaiters(WaitersEntity waitersEntity){
        WaitersEntity waiter= waitersRepository.save(waitersEntity);
        return Waiters.toModel(waiter);
    }

    public Waiters updateWaiters(Long id,WaitersEntity waitersEntity) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        waiters.setFirstName(waitersEntity.getFirstName());
        waiters.setLastName(waitersEntity.getLastName());
        waiters.setMiddleName(waitersEntity.getMiddleName());
        waiters.setPosition(waitersEntity.getPosition());
        waiters.setRating(waitersEntity.getRating());
        waiters.setDateOfEntry(waitersEntity.getDateOfEntry());
        waiters.setWaitersAchievements(waitersEntity.getWaitersAchievements());
        return Waiters.toModel(waitersRepository.save(waiters));
    }

    public Long deleteWaiter(Long id) throws WaiterNotFoundException {
        WaitersEntity waiters = waitersRepository.findById(id).get();
        if(waiters==null){
            throw new WaiterNotFoundException("Пользователь не найден");
        }
        waitersRepository.deleteById(id);
        return id;
    }
}
