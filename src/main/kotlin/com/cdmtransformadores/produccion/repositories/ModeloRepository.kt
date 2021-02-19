package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.Modelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ModeloRepository : JpaRepository<Modelo, String> {

    @Query(
        value = "SELECT m.id_modelo, m.cantidad_aceite, m.fase, m.kva, m.nombre_modelo, m.peso, m.tipo_aceite, m.tipo_transformador, m.design_id, count(t) as stock from modelos m \nJOIN transformadores t ON m.id_modelo = t.modelo_id_modelo \nWHERE t.cliente = 'CDM' \nGROUP BY m.id_modelo",
        nativeQuery = true
    )
    fun getAllWithStock(): List<Modelo>
}
