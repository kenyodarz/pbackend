package com.cdmtransformadores.produccion.services

import com.cdmtransformadores.produccion.models.Section
import com.cdmtransformadores.produccion.models.enums.ESection
import com.cdmtransformadores.produccion.repositories.SectionRepository
import com.cdmtransformadores.produccion.services.apis.SectionServiceAPI
import com.cdmtransformadores.produccion.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SectionServiceImpl(repository: SectionRepository) : GenericServiceImpl<Section, Long>(), SectionServiceAPI {

    private val repository: SectionRepository

    init {
        repository.also { this.repository = it }
    }


    override fun findByNombre(nombre: ESection): Optional<Section> {
        return this.repository.findByNombre(nombre)
    }

    override fun getRepository(): JpaRepository<Section, Long> {
        return this.repository
    }

}