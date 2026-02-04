package com.example.demo.mappers

import InventoryRuleRequest
import com.example.demo.dtos.InventoryRuleResponse
import com.example.demo.models.InventoryRule

object InventoryRuleMapper {

    fun toEntity(request: InventoryRuleRequest, userId: String): InventoryRule =
        InventoryRule(
            name = request.name,
            description = request.description,
            isActive = request.isActive ?: true,
            updatedBy = userId
        )

    fun toResponse(entity: InventoryRule): InventoryRuleResponse =
        InventoryRuleResponse(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            isActive = entity.isActive,
            updatedBy = entity.updatedBy,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
}
