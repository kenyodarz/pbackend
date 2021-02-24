package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.core.GenerarSerie
import com.cdmtransformadores.produccion.models.Transformador
import com.cdmtransformadores.produccion.services.apis.TransformadorServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/transformadores")
class TransformadorRestController(override var serviceAPI: TransformadorServiceAPI) :
    GenericRestController<Transformador, String>(serviceAPI) {

    @GetMapping("/terminados")
    fun findAllByInProductionIsFalse(): ResponseEntity<List<Transformador>> {
        return ResponseEntity.ok(serviceAPI.getAllByInProductionIsFalse())
    }

    @PostMapping("/generar/{cantidad}/{id_order_production}")
    fun generarXCantidadDeTransformadores(
        @RequestBody @Valid entity: Transformador,
        result: BindingResult,
        @PathVariable cantidad: Int,
        @PathVariable id_order_production: String
    ): ResponseEntity<*> {
        if (result.hasErrors()) validar(result)
        val generarSerie = GenerarSerie()
        for (num in 1..cantidad) {
            entity.numeroSerie = generarSerie.numeroSerie(this.serviceAPI.findMAxNumeroSerie())
            this.serviceAPI.save(entity)
            entity.numeroSerieInterno = null
        }
        return ResponseEntity.ok().body("Generados los $cantidad correctamente")
    }

}
