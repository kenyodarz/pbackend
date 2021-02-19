package com.cdmtransformadores.produccion.models

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "modelos")
class Modelo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var idModelo: String? = null

    @Column
    @NotBlank
    var nombreModelo: String? = null

    @Column
    var kva: Int?=null

    @Column
    var fase: Int?=null

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

    @Column(insertable = false, updatable = false)
    var stock: Int?=null

    @OneToOne
    @NotNull
    var design: Design?=null

    @PrePersist
    fun prePersist() {
        design!!.kva.also { this.kva = it }
        design!!.fase.also { this.fase = it }

    }

}
