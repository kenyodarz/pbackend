package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.models.Design
import com.cdmtransformadores.produccion.services.apis.DesignServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/designs")
class DesignRestController(serviceAPI: DesignServiceAPI) : GenericRestController<Design, Long>(serviceAPI)