package com.cdmtransformadores.produccion.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "transformadores",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("numeroSerie"))])
class Transformador {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var numeroSerieInterno: String? = null

    @Column
    var numeroSerie: String? = null

    @Column
    var kva: Int? = null

    @Column
    var fase: Int? = null

    @Column
    var peso: Int? = null

    @Column
    var cantidadAceite: Int? = null

    @Column
    var tipoAceite: String? = null

    @Column
    var tipoTransformador: String? = null

    @Column
    var fecha: Int? = null

    @Column
    var nominalPosition: Int? = null

    @OneToOne
    @NotNull
    var modelo: Modelo? = null

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var createAt: Date? = null

    @Column
    var inProduction: Boolean? = null

    @Column
    var cliente: String? = null

    @PrePersist
    fun prePersist() {
        modelo!!.kva.also { this.kva = it }
        modelo!!.fase.also { this.fase = it }
        modelo!!.peso.also { this.peso = it }
        modelo!!.cantidadAceite.also { this.cantidadAceite = it }
        modelo!!.tipoAceite.also { this.tipoAceite = it }
        modelo!!.tipoTransformador.also { this.tipoTransformador = it }
        LocalDate.now().year.also { this.fecha = it }
        modelo!!.design!!.positionNominalConmutador.also { this.nominalPosition = it }
        Date().also { createAt = it }
        true.also { inProduction = it }
        "CDM".also { this.cliente = it }
    }
}
