package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.core.GenerarSerie
import com.cdmtransformadores.produccion.models.ProductionOrder
import com.cdmtransformadores.produccion.models.Transformador
import com.cdmtransformadores.produccion.services.apis.ProductionOrderServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/ordenes")
class ProductionOrderRestController(override var serviceAPI: ProductionOrderServiceAPI) :
    GenericRestController<ProductionOrder, String>(serviceAPI) {

    @GetMapping("/serie")
    fun obtenerNumeroSerie(): ResponseEntity<GenerarSerie> {
        val generarSerie = GenerarSerie()
        generarSerie.numeroSerie(serviceAPI.findMaxNumeroOrden())
        return ResponseEntity.accepted().body(generarSerie)
    }

    @PutMapping("/{idProductionOrder}/asignar-transformadores")
    fun asignarTransformadores(
        @RequestBody transformador: Transformador,
        @PathVariable idProductionOrder: String,
    ): ResponseEntity<*> {
        val order: ProductionOrder = serviceAPI.getOne(idProductionOrder)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se Encontró la orden")
        order.addTransformador(transformador)
        return ResponseEntity.accepted().body(serviceAPI.save(order))
    }

    @PutMapping("/{idProductionOrder}/eliminar-transformadores")
    fun eliminarTransformadores(
        @RequestBody transformador: Transformador,
        @PathVariable idProductionOrder: String,
    ): ResponseEntity<*> {
        val order: ProductionOrder = serviceAPI.getOne(idProductionOrder)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se Encontró la orden")
        order.removeTransformador(transformador)
        return ResponseEntity.accepted().body(serviceAPI.save(order))
    }

}
