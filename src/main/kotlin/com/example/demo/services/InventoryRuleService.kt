package com.example.demo.services

import com.example.demo.models.InventoryRule
import com.example.demo.repository.InventoryRuleRepository
import org.springframework.stereotype.Service

@Service
class InventoryRuleService(
    private val repository: InventoryRuleRepository
) {

    fun findAll(): List<InventoryRule> =
        repository.findAll()

    fun findById(id: Long): InventoryRule =
        repository.findById(id)
            .orElseThrow { NotFoundException("Rule $id no existe") }

    fun create(request: InventoryRuleRequest, auth: Authentication): InventoryRule {
        val userId = JwtUtils.extractUserId(auth)

        if (request.name.isBlank()) {
            throw BadRequestException("Name es requerido")
        }

        return repository.save(
            InventoryRuleMapper.toEntity(request, userId)
        )
    }

    fun update(id: Long, request: InventoryRuleRequest, auth: Authentication): InventoryRule {
        val userId = JwtUtils.extractUserId(auth)
        val rule = findById(id)

        rule.name = request.name
        rule.description = request.description
        rule.isActive = request.isActive ?: rule.isActive
        rule.updatedBy = userId

        return repository.save(rule)
    }

    fun toggle(id: Long, auth: Authentication): InventoryRule {
        val userId = JwtUtils.extractUserId(auth)
        val rule = findById(id)

        rule.isActive = !rule.isActive
        rule.updatedBy = userId

        return repository.save(rule)
    }
}
