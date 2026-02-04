package com.example.demo.repository

import com.example.demo.models.InventoryRule
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRuleRepository : JpaRepository<InventoryRule, Long> {

    fun findByIsActiveTrue(): List<InventoryRule>

    fun findByNameContainingIgnoreCase(name: String): List<InventoryRule>
}

