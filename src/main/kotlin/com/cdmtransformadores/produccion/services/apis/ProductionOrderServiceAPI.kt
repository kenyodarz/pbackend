package com.cdmtransformadores.produccion.services.apis

import com.cdmtransformadores.produccion.models.ProductionOrder
import com.cdmtransformadores.produccion.shared.GenericServiceAPI
import java.util.*

interface ProductionOrderServiceAPI : GenericServiceAPI<ProductionOrder, String> {
    fun findProductionOrderByNumeroSerie(numeroSerie: String): ProductionOrder?
    fun findMaxNumeroOrden(): Int
}
