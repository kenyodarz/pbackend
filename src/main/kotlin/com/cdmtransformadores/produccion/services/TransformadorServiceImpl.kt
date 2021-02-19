package com.cdmtransformadores.produccion.services

import com.cdmtransformadores.produccion.models.Transformador
import com.cdmtransformadores.produccion.repositories.TransformadorRepository
import com.cdmtransformadores.produccion.services.apis.TransformadorServiceAPI
import com.cdmtransformadores.produccion.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class TransformadorServiceImpl(repository: TransformadorRepository) :
    GenericServiceImpl<Transformador, String>(), TransformadorServiceAPI {

    private val repository: TransformadorRepository

    init {
        repository.also { this.repository = it }
    }

    override fun getAllByInProductionIsFalse(): List<Transformador> {
        return this.repository.getAllByInProductionIsFalse()
    }

    override fun getRepository(): JpaRepository<Transformador, String> {
        return this.repository
    }

    override fun findMAxNumeroSerie(): Int {
        return this.repository.findMAxNumeroSerie().orElse(0)
    }
}
