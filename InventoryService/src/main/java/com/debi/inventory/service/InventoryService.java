package com.debi.inventory.service;

import org.springframework.stereotype.Service;

import com.debi.inventory.repository.OrderInventoryRepository;
import com.debi.inventory.dto.InventoryDto;
import com.debi.inventory.dto.events.inventory.InventoryEvent;
import com.debi.inventory.dto.events.inventory.InventoryStatus;
import com.debi.inventory.dto.events.order.OrderEvent;
import com.debi.inventory.entity.OrderInventoryConsumption;
import com.debi.inventory.repository.OrderInventoryConsumptionRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final OrderInventoryRepository orderInventoryRepository;
    private final OrderInventoryConsumptionRepository orderInventoryConsumptionRepository;

    public InventoryService(OrderInventoryRepository orderInventoryRepository,
                            OrderInventoryConsumptionRepository orderInventoryConsumptionRepository) {
        this.orderInventoryRepository = orderInventoryRepository;
        this.orderInventoryConsumptionRepository = orderInventoryConsumptionRepository;
    }

    @Transactional
    public InventoryEvent newOrderInventory(OrderEvent orderEvent){
        InventoryDto dto = InventoryDto.of(orderEvent.getPurchaseOrder().getOrderId(), orderEvent.getPurchaseOrder().getProductId());
        return orderInventoryRepository.findById(orderEvent.getPurchaseOrder().getProductId())
                .filter(i -> i.getAvailableInventory() > 0 )
                .map(i -> {
                    i.setAvailableInventory(i.getAvailableInventory() - 1);
                    orderInventoryConsumptionRepository.save(OrderInventoryConsumption.of(orderEvent.getPurchaseOrder().getOrderId(), orderEvent.getPurchaseOrder().getProductId(), 1));
                    return new InventoryEvent(dto, InventoryStatus.RESERVED);
                })
                .orElse(new InventoryEvent(dto, InventoryStatus.REJECTED));
    }

    @Transactional
    public void cancelOrderInventory(OrderEvent orderEvent){
        orderInventoryConsumptionRepository.findById(orderEvent.getPurchaseOrder().getOrderId())
                .ifPresent(ci -> {
                    orderInventoryRepository.findById(ci.getProductId())
                            .ifPresent(i ->
                                i.setAvailableInventory(i.getAvailableInventory() + ci.getQuantityConsumed())
                            );
                    orderInventoryConsumptionRepository.delete(ci);
                });
    }
}
