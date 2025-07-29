package com.debi.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debi.inventory.entity.OrderInventory;

@Repository
public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {
    
}
