package com.cdmtransformadores.produccion.controllers

import com.cdmtransformadores.produccion.models.Section
import com.cdmtransformadores.produccion.models.SectionTransformador
import com.cdmtransformadores.produccion.models.enums.ESection
import com.cdmtransformadores.produccion.services.apis.SectionServiceAPI
import com.cdmtransformadores.produccion.services.apis.SectionTransformadorServiceAPI
import com.cdmtransformadores.produccion.shared.GenericRestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/sections")
class SectionTransformadorRestController(override var serviceAPI: SectionTransformadorServiceAPI) :
    GenericRestController<SectionTransformador, Long>(serviceAPI) {

    @Autowired
    private val sectionServiceAPI: SectionServiceAPI?=null

    override fun save(entity: SectionTransformador, result: BindingResult, section: String): ResponseEntity<*> {
        if (result.hasErrors()) { super.validar(result) }
        val newSection: Section
        when(section){
            "DESENCUBE" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.DESENCUBE).orElse(null)
            }
            "DESARME" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.DESARME).orElse(null)
            }
            "BOBINADO" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.BOBINADO).orElse(null)
            }
            "METALMECANICA" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.METALMECANICA).orElse(null)
            }
            "LAVADO" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.LAVADO).orElse(null)
            }
            "PINTURA" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.PINTURA).orElse(null)
            }
            "ENSAMBLE" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.ENSAMBLE).orElse(null)
            }
            "CONEXIONES" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.CONEXIONES).orElse(null)
            }
            "HORNO" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.HORNO).orElse(null)
            }
            "ENCUBE" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.ENCUBE).orElse(null)
            }
            "PRUEBA" -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.PRUEBA).orElse(null)
            }
            "ALMACEN"  -> {
                newSection = sectionServiceAPI!!.findByNombre(ESection.ALMACEN).orElse(null)
            }
            else -> return ResponseEntity.badRequest().body("Sección Invalida")
        }
        entity.addSection(newSection)
        return ResponseEntity.ok(serviceAPI.save(entity))
    }
}