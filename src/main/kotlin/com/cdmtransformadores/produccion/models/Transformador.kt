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
    var fecha: LocalDate? = null

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
        LocalDate.now().also { this.fecha = it }
        modelo!!.design!!.positionNominalConmutador.also { this.nominalPosition = it }
        Date().also { createAt = it }
        true.also { inProduction = it }
        "CDM".also { this.cliente = it }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Transformador) {
            return false
        }
        return numeroSerie != null && numeroSerie == other.numeroSerie
    }

    override fun hashCode(): Int {
        var result = numeroSerie?.hashCode() ?: 0
        result = 31 * result + (kva ?: 0)
        result = 31 * result + (fase ?: 0)
        result = 31 * result + (peso ?: 0)
        result = 31 * result + (cantidadAceite ?: 0)
        result = 31 * result + (tipoAceite?.hashCode() ?: 0)
        result = 31 * result + (tipoTransformador?.hashCode() ?: 0)
        result = 31 * result + (fecha?.hashCode() ?: 0)
        result = 31 * result + (nominalPosition ?: 0)
        result = 31 * result + (modelo?.hashCode() ?: 0)
        result = 31 * result + (createAt?.hashCode() ?: 0)
        result = 31 * result + (inProduction?.hashCode() ?: 0)
        return result
    }
}
