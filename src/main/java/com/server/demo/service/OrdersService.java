package com.server.demo.service;

import com.server.demo.entities.OrdersEntity;
import com.server.demo.entities.RatingEntity;
import com.server.demo.exception.OrderNotFoundException;
import com.server.demo.model.*;
import com.server.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    WaitersRepository waitersRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    AchievementsRepository achievementsRepository;

    @Autowired
    AchievementsService achievementsService;

    @Autowired
    DishOrderRepository dishOrderRepository;

    @Autowired
    MissionService missionService;

    
    public Orders ordersCreate(OrdersForCreate ordersForCreate){
        if(ordersForCreate.getOrderTime()==null){
            ordersForCreate.setOrderTime(Date.from(ZonedDateTime.now().toInstant()));
        }
        return Orders.toModel((OrdersEntity.toEntity(ordersForCreate,waitersRepository,menuRepository,dishOrderRepository,ordersRepository)));
    }

    public Orders ordersClosedPlugin(OrdersPlugin ordersPlugin) throws OrderNotFoundException {
        if(ordersPlugin.getItems().size()==0){
            ordersPlugin.getItems().add(new DishOrder(1l,3,false));
        }
        OrdersEntity ordersEntity = OrdersEntity.toNewEntity(ordersPlugin,waitersRepository,menuRepository,dishOrderRepository,ordersRepository);
        Orders orders = ordersCompleted(ordersEntity.getId());
        return orders;
    }

    public Long ordersDelete(Long id) throws OrderNotFoundException {
        OrdersEntity orders=ordersRepository.findById(id).get();
        if(orders==null){
            throw new OrderNotFoundException("Заказ не найден");
        }
        ordersRepository.deleteById(id);
        return id;
    }

    public Orders ordersUpdate(Long id, OrdersEntity ordersEntity) throws OrderNotFoundException {
        OrdersEntity orders = ordersRepository.findById(id).get();
        if(orders==null){
            throw new OrderNotFoundException("Заказ не найден");
        }
        orders.setOrderPrice(ordersEntity.getOrderPrice());
        orders.setDishOrder(ordersEntity.getDishOrder());
        orders.setOrderTime(ordersEntity.getOrderTime());
        orders.setId(ordersEntity.getId());
        orders.setOrderStatus(ordersEntity.isOrderStatus());
        orders.setWaitersEntity(ordersEntity.getWaitersEntity());
        if(orders.isOrderStatus()){
            achievementsService.checkAllAchievements(orders);
        }
        return Orders.toModel(ordersRepository.save(orders));
    }

    public Orders ordersCompleted(Long id) throws OrderNotFoundException {
        OrdersEntity ordersEntity=ordersRepository.findById(id).get();
        if(ordersEntity==null){
            throw new OrderNotFoundException("Заказ не найден");
        }
        if(!ordersEntity.isOrderStatus()){
            ordersEntity.setOrderStatus(!ordersEntity.isOrderStatus());
        }
        if(ordersEntity.getOrderTime()==null){
            ordersEntity.setOrderTime(Date.from(ZonedDateTime.now().toInstant()));
        }
        if(ordersEntity.isOrderStatus()){
            achievementsService.checkAllAchievements(ordersEntity);
            missionService.checkAllMissions(ordersEntity);
        }
        return Orders.toModel(ordersRepository.save(ordersEntity));

    }

    public List<Orders> ordersGetAll(){
        Iterable<OrdersEntity> orders= ordersRepository.findAll();
        List<OrdersEntity> resultEntity=new ArrayList<>();
        orders.forEach(a->resultEntity.add(a));
        return resultEntity.stream().map(Orders::toModel).collect(Collectors.toList());
    }

}
