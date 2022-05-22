/*
package com.server.demo.config.socket;


import com.server.demo.model.Orders;
import com.server.demo.model.OrdersForCreate;
import com.server.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    OrdersService ordersService;

    @Autowired
    public WebSocketHandler(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public WebSocketHandler() {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
        System.out.println(message);
        ObjectMapper mapper = new ObjectMapper();
        OrdersForCreate orders=mapper.readValue(message.getPayload(),OrdersForCreate.class);
        Orders newOrder=ordersService.ordersCreate(orders);
        session.sendMessage(new TextMessage(mapper.writeValueAsString(newOrder)));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        System.out.println("New Binary Message Received");
        session.sendMessage(message);
    }
}


*/
