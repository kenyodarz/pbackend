package com.cdmtransformadores.produccion.services

import com.cdmtransformadores.produccion.models.Design
import com.cdmtransformadores.produccion.repositories.DesignRepository
import com.cdmtransformadores.produccion.services.apis.DesignServiceAPI
import com.cdmtransformadores.produccion.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class DesignServiceImpl(repository: DesignRepository): GenericServiceImpl<Design, Long>(), DesignServiceAPI {

    private val repository: DesignRepository

    init {
        repository.also { this.repository = it }
    }

    override fun getRepository(): JpaRepository<Design, Long> {
        return this.repository
    }
}