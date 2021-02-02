package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.Design
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DesignRepository : JpaRepository<Design, Long>