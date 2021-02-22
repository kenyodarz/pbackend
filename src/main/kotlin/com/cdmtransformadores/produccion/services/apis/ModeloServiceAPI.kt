package com.cdmtransformadores.produccion.services.apis

import com.cdmtransformadores.produccion.models.Modelo
import com.cdmtransformadores.produccion.shared.GenericServiceAPI
import java.util.*

interface ModeloServiceAPI : GenericServiceAPI<Modelo, String> {

    fun getAllWithStock(): List<Modelo>

    fun existsByNombreModelo(nombreModelo: String): Boolean

}
