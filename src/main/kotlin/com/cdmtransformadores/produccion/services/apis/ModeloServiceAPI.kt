package com.cdmtransformadores.produccion.services.apis

import com.cdmtransformadores.produccion.models.Modelo
import com.cdmtransformadores.produccion.shared.GenericServiceAPI
import org.springframework.data.jpa.repository.Query

interface ModeloServiceAPI : GenericServiceAPI<Modelo, String> {

    fun getAllWithStock(): List<Modelo>

    fun findByNombreModelo(nombreModelo: String): Modelo?

    fun existsByNombreModelo(nombreModelo: String): Boolean

}
