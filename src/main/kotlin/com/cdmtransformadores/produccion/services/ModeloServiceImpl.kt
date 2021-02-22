package com.cdmtransformadores.produccion.services

import com.cdmtransformadores.produccion.models.Modelo
import com.cdmtransformadores.produccion.repositories.ModeloRepository
import com.cdmtransformadores.produccion.services.apis.ModeloServiceAPI
import com.cdmtransformadores.produccion.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.validation.constraints.NotNull

@Service
class ModeloServiceImpl(repository: ModeloRepository) : GenericServiceImpl<Modelo, String>(),
    ModeloServiceAPI {

    private lateinit var repository: ModeloRepository

    init {
        repository.also { this.repository = it }
    }

    override fun getRepository(): JpaRepository<Modelo, String> {
        return this.repository
    }

    override fun getAllWithStock(): List<Modelo> {
        return this.repository.getAllWithStock()
    }


    @NotNull
    override fun existsByNombreModelo(nombreModelo: String): Boolean {
        return this.repository.existsByNombreModelo(nombreModelo)
    }
}
