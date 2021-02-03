package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.SectionTransformador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SectionTransformadorRepository : JpaRepository<SectionTransformador, Long>