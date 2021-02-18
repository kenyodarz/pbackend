package com.cdmtransformadores.produccion.services.apis

import com.cdmtransformadores.produccion.models.Transformador
import com.cdmtransformadores.produccion.shared.GenericServiceAPI

interface TransformadorServiceAPI: GenericServiceAPI<Transformador, String> {
    fun getAllByInProductionIsFalse(): List<Transformador>
}
