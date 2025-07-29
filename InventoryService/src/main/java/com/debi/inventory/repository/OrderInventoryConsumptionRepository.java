package com.debi.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debi.inventory.entity.OrderInventoryConsumption;
import java.util.UUID;

@Repository
public interface OrderInventoryConsumptionRepository extends JpaRepository<OrderInventoryConsumption, UUID>{
    
}
