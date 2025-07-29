package com.debi.payment.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.debi.payment.dto.events.order.OrderEvent;
import com.debi.payment.dto.events.payment.PaymentEvent;
import com.debi.payment.dto.events.order.OrderStatus;
import com.debi.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  


@Configuration
public class PaymentConfig {
    Logger log = LoggerFactory.getLogger(PaymentConfig.class);

    @Autowired
    private PaymentService service;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return flux -> flux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent event){
        if(event.getOrderStatus().equals(OrderStatus.ORDER_CREATED)){
            return Mono.fromSupplier(() -> {
                PaymentEvent paymentEvent = this.service.newOrderEvent(event);
                log.info("newOrderInventory() returned: {}", paymentEvent.getPaymentStatus());
                return paymentEvent;
                }
            );
        }else{
            return Mono.fromRunnable(() -> this.service.cancelOrderEvent(event));
        }
    }

}
