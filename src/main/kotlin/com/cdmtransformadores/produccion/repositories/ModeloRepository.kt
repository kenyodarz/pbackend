package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.Modelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ModeloRepository : JpaRepository<Modelo, String> {

    @Query(
        value = """ 
            SELECT m.id_modelo, m.cantidad_aceite, m.fase, m.kva, m.nombre_modelo, m.peso, m.tipo_aceite, m.tipo_transformador, m.design_id, 
                count(t.in_production = false OR null) AS stock
            FROM modelos m
            JOIN transformadores t ON m.id_modelo = t.modelo_id_modelo 
            WHERE t.cliente = 'CDM'
            GROUP BY m.id_modelo
            """,
        nativeQuery = true
    )
    fun getAllWithStock(): List<Modelo>

    fun existsByNombreModelo(nombreModelo: String): Boolean
}
