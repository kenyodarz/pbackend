package com.cdmtransformadores.produccion.security.repositories

import com.cdmtransformadores.produccion.security.models.ERole
import com.cdmtransformadores.produccion.security.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: ERole): Optional<Role>
}