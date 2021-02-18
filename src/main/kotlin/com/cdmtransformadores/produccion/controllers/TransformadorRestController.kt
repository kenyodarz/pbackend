package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.models.Transformador
import com.cdmtransformadores.produccion.services.apis.TransformadorServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/transformadores")
class TransformadorRestController(override var serviceAPI: TransformadorServiceAPI):
GenericRestController<Transformador, String>(serviceAPI) {

    @GetMapping("/terminados")
    fun findAllByInProductionIsFalse(): ResponseEntity<List<Transformador>>{
        return ResponseEntity.ok(serviceAPI.getAllByInProductionIsFalse())
    }

}
