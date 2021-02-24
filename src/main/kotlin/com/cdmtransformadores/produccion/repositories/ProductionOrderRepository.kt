package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.ProductionOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductionOrderRepository : JpaRepository<ProductionOrder, String>{

    @Query("select o from ProductionOrder o join fetch o.transformadores t where t.numeroSerie=?1")
    fun findProductionOrderByNumeroSerie(numeroSerie: String): Optional<ProductionOrder>

    @Query(value = "select max(o.numeroOrden) from ProductionOrder o")
    fun findMaxNumeroOrden(): Optional<Int>

}
