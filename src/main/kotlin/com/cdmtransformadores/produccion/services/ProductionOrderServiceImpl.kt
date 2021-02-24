package com.cdmtransformadores.produccion.services

import com.cdmtransformadores.produccion.models.ProductionOrder
import com.cdmtransformadores.produccion.repositories.ProductionOrderRepository
import com.cdmtransformadores.produccion.services.apis.ProductionOrderServiceAPI
import com.cdmtransformadores.produccion.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ProductionOrderServiceImpl(repository: ProductionOrderRepository): GenericServiceImpl<ProductionOrder, String>(), ProductionOrderServiceAPI {

    private lateinit var repository: ProductionOrderRepository

    init {
        repository.also { this.repository = it }
    }

    override fun getRepository(): JpaRepository<ProductionOrder, String> {
        return this.repository
    }

    override fun findProductionOrderByNumeroSerie(numeroSerie: String): ProductionOrder? {
        return this.repository.findProductionOrderByNumeroSerie(numeroSerie).orElse(null)
    }

    override fun findMaxNumeroOrden(): Int {
        val numeroSerie = this.repository.findMaxNumeroOrden()
        return numeroSerie.orElse(0)
    }
}
