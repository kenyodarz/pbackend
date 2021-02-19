package com.cdmtransformadores.produccion.repositories

import com.cdmtransformadores.produccion.models.Transformador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransformadorRepository : JpaRepository<Transformador, String> {

    @Query("select t from Transformador t where t.inProduction=false")
    fun getAllByInProductionIsFalse(): List<Transformador>

    @Query("select max(t.numeroSerie) from Transformador t")
    fun findMAxNumeroSerie(): Optional<Int>

}
