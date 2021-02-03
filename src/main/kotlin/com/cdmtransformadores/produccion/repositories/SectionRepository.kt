package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.Section
import com.cdmtransformadores.produccion.models.enums.ESection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SectionRepository: JpaRepository<Section, Long> {
    fun findByNombre(nombre: ESection): Optional<Section>
}