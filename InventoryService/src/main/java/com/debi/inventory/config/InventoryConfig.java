package com.debi.inventory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;
import com.debi.inventory.dto.events.order.OrderEvent;
import com.debi.inventory.dto.events.order.OrderStatus; 
import com.debi.inventory.service.InventoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.debi.inventory.dto.events.inventory.InventoryEvent;

@Configuration
public class InventoryConfig {

     Logger log = LoggerFactory.getLogger(InventoryConfig.class);

    private final InventoryService service;
    public InventoryConfig(InventoryService service) {
        this.service = service;
    }
    /**
     * This function processes incoming OrderEvents and returns a Flux of InventoryEvents.
     * It checks the status of the order and either reserves inventory for a new order or cancels inventory for an existing order.
     * @return
     */
    @Bean
    public Function<Flux<OrderEvent>, Flux<InventoryEvent>> inventoryProcessor() {
        return flux -> flux.flatMap(this::processInventory);
    }

    private Mono<InventoryEvent> processInventory(OrderEvent event){
        if(event.getOrderStatus().equals(OrderStatus.ORDER_CREATED)){
            //return Mono.fromSupplier(() -> this.service.newOrderInventory(event));
            return Mono.fromSupplier(() -> {
                InventoryEvent inventoryEvent = this.service.newOrderInventory(event);
                log.info("newOrderInventory() returned: {}", inventoryEvent.getStatus());
                return inventoryEvent;
                }
            );
        }
        
        return Mono.fromRunnable(() -> this.service.cancelOrderInventory(event));
    }

}