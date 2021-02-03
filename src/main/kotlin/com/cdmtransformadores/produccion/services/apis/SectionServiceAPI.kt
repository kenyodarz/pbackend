package com.cdmtransformadores.produccion.services.apis

import com.cdmtransformadores.produccion.models.Section
import com.cdmtransformadores.produccion.models.enums.ESection
import com.cdmtransformadores.produccion.shared.GenericServiceAPI
import java.util.*

interface SectionServiceAPI: GenericServiceAPI<Section, Long> {
    fun findByNombre(nombre: ESection): Optional<Section>
}