package com.cdmtransformadores.produccion.services.apis

import com.cdmtransformadores.produccion.models.Modelo
import com.cdmtransformadores.produccion.shared.GenericServiceAPI

interface ModeloServiceAPI: GenericServiceAPI<Modelo, String> {

    fun getAllWithStock(): List<Modelo>

}
