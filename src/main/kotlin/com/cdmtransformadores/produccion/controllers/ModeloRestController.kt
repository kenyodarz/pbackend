package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.models.Modelo
import com.cdmtransformadores.produccion.services.apis.ModeloServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/modelos")
class ModeloRestController(override var serviceAPI: ModeloServiceAPI) :
    GenericRestController<Modelo, String>(serviceAPI) {

    @GetMapping("/with-stock")
    fun getAllWithStock(): ResponseEntity<List<Modelo>> {
        return ResponseEntity.ok().body(this.serviceAPI.getAllWithStock())
    }

    override fun save(entity: Modelo, result: BindingResult): ResponseEntity<*> {
        return when {
            result.hasErrors() -> validar(result)
            !this.serviceAPI.existsByNombreModelo(entity.nombreModelo.toString()) -> {
                ResponseEntity.badRequest().body("Ya existe un Modelo con ese nombre")
            }
            else -> ResponseEntity.status(HttpStatus.CREATED)
                .body(this.serviceAPI.save(entity))
        }
    }

}
