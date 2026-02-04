package com.example.demo.controllers

import InventoryRuleRequest
import com.example.demo.dtos.InventoryRuleResponse
import com.example.demo.mappers.InventoryRuleMapper
import com.example.demo.services.InventoryRuleService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.map

@RestController
@RequestMapping("/api/rules")
class InventoryRuleController(
    private val service: InventoryRuleService
) {

    @GetMapping
    fun list(): List<InventoryRuleResponse> =
        service.findAll().map(InventoryRuleMapper::toResponse)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): InventoryRuleResponse =
        InventoryRuleMapper.toResponse(service.findById(id))

    @PostMapping
    fun create(
        @RequestBody request: InventoryRuleRequest,
        auth: Authentication
    ) = InventoryRuleMapper.toResponse(service.create(request, auth))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: InventoryRuleRequest,
        auth: Authentication
    ) = InventoryRuleMapper.toResponse(service.update(id, request, auth))

    @PatchMapping("/{id}/toggle")
    fun toggle(
        @PathVariable id: Long,
        auth: Authentication
    ) = InventoryRuleMapper.toResponse(service.toggle(id, auth))
}
