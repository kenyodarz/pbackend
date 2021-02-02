package com.cdmtransformadores.produccion.models

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "transformadores")
class Transformador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val numeroSerie: Long?=null

    @NotNull
    @Column
    var kva: Int?=null

    @NotNull
    @Column
    var fase: Int?=null

    @NotNull
    @Column
    var peso: Int?=null

    @NotNull
    @Column
    var cantidadAceite: Int?=null

    @NotBlank
    @Column
    var tipoAceite: String?=null

    @NotBlank
    @Column
    var tipoTransformador: String?=null

    @Column
    var fecha: LocalDate?=null

    @NotNull
    @Column
    var nominalPosition: Int?=null

    @OneToOne
    @NotNull
    var design: Design?=null

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var createAt: Date? = null

    @Column
    var inProduction: Boolean?=null

    @PrePersist
    fun prePersist() {
        Date().also { createAt = it }
        true.also { inProduction = it }
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
        result = 31 * result + (design?.hashCode() ?: 0)
        result = 31 * result + (createAt?.hashCode() ?: 0)
        result = 31 * result + (inProduction?.hashCode() ?: 0)
        return result
    }
}