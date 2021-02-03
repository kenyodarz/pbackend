package com.cdmtransformadores.produccion.services

import com.cdmtransformadores.produccion.models.SectionTransformador
import com.cdmtransformadores.produccion.repositories.SectionTransformadorRepository
import com.cdmtransformadores.produccion.services.apis.SectionTransformadorServiceAPI
import com.cdmtransformadores.produccion.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class SectionTransformadorServiceImpl(repository: SectionTransformadorRepository) :
    GenericServiceImpl<SectionTransformador, Long>(), SectionTransformadorServiceAPI {

    private val repository: SectionTransformadorRepository

    init {
        repository.also { this.repository = it }
    }

    override fun getRepository(): JpaRepository<SectionTransformador, Long> {
        return this.repository
    }
}