package com.debi.order.config;
import com.debi.order.dto.events.inventory.InventoryEvent;
import com.debi.order.dto.events.inventory.InventoryStatus;
import com.debi.order.dto.events.payment.PaymentEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

import com.debi.order.dto.events.payment.PaymentStatus;


@Configuration
public class EventHandlersConfig {

    Logger log = LoggerFactory.getLogger(EventHandlersConfig.class);

    @Autowired
    private OrderStatusUpdateEventHandler orderEventHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return pe -> {
            orderEventHandler.updateOrder(pe.getPayment().getOrderId(), po -> {
                 log.info("Payment order: {} and status {}", pe.getPayment().getOrderId()
                ,pe.getPaymentStatus());
                System.out.println("Payment order: " + pe.getPayment().getOrderId());
                po.setInventoryStatus(InventoryStatus.RESERVED);
                po.setPaymentStatus(pe.getPaymentStatus());
            });
        };
    }

    @Bean
    public Consumer<InventoryEvent> inventoryEventConsumer(){
        return ie -> {
            orderEventHandler.updateOrder(ie.getInventory().getOrderId(), po -> {
                log.info("Invertory order: {} and status {}", ie.getInventory().getOrderId()
                ,ie.getStatus());
                po.setPaymentStatus(PaymentStatus.RESERVED);
                po.setInventoryStatus(ie.getStatus());
            });
        };
    }

}
