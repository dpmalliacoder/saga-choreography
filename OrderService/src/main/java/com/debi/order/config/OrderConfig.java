package com.debi.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import java.util.function.Supplier;
import com.debi.order.dto.events.order.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class OrderConfig {
    Logger log = LoggerFactory.getLogger(OrderConfig.class);

    @Bean
    public Sinks.Many<OrderEvent> orderSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sink){
         return () -> sink.asFlux().doOnNext(e -> log.info("Sending to Kafka: {}", e));
        //return sink::asFlux;
    }

}