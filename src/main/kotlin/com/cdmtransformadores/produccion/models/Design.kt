package com.cdmtransformadores.produccion.models

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table
class Design {
    @Id
    @NotBlank
    var id: String?=null

    @Column
    @NotBlank
    var description:String?=null

    @Column
    @NotNull
    var fecha: LocalDate?=null

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

    @NotNull
    @Column
    var tipoAceite: String?=null

    @NotBlank
    @Column
    var tipoTransformador: String?=null
}