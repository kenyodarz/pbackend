package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.models.Modelo
import com.cdmtransformadores.produccion.services.apis.ModeloServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/modelos")
class ModeloRestController(override var serviceAPI: ModeloServiceAPI) :
    GenericRestController<Modelo, String>(serviceAPI) {

    @GetMapping("/with-stock")
    fun getAllWithStock(): ResponseEntity<List<Modelo>> {
        return ResponseEntity.ok().body(this.serviceAPI.getAllWithStock())
    }

}
