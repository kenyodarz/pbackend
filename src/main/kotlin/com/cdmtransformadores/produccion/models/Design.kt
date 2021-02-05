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
    var id: String? = null

    @NotNull
    @Column
    var kva: Int? = null

    @Column
    @NotBlank
    var marca: String? = null

    @Column
    @NotNull
    var fecha: LocalDate? = null

    @NotNull
    @Column
    var fase: Int? = null

    @NotNull
    @Column
    var voltajeNominalPrimario: Int? = null

    @NotNull
    @Column
    var voltajeNominalSecundario: Int? = null

    @Column
    @NotBlank
    var tipoDeNucleo: String? = null

    @Column
    @NotBlank
    var formaDelNucleo: String? = null

    @Column
    @NotNull
    var cantidadDeFe: Int? = null

    @Column
    @NotNull
    var anchoDeFe: Int? = null

    @Column
    @NotNull
    var anchoVentana: Int? = null

    @Column
    @NotNull
    var altoVentana: Int? = null

    @Column
    @NotBlank
    var conductorSecundario: String? = null

    @Column
    @NotBlank
    var grupoConnection: String? = null

    @Column
    @NotBlank
    var tipoLaminaMagnetic: String? = null

    @Column
    @NotNull
    var positionNominalConmutador: Int? = null

    @NotNull
    @Column
    var pesoNucleo1: Double? = null

    @Column
    var pesoNucleo2: Double? = null

    @NotNull
    @Column
    var pesoTotal: Double? = null

    @Column
    @NotBlank
    var poW: Int? = null

    @Column
    @NotBlank
    var induction: String? = null

    @Column
    @NotNull
    var inductionTDesign: Double? = null

    @Column
    @NotBlank
    var connectionBajaTension: String? = null

    @Column
    @NotBlank
    var connectionAltaTension: String? = null

    @Column
    @NotNull
    var lmn1: Double? = null

    @Column
    @NotNull
    var lmn2: Double? = null

    @Column
    @NotNull
    var anet: Double? = null

    @Column
    @NotNull
    var wkgCalculado: Double? = null

    @Column
    @NotNull
    var wkgTablaLamina: Double? = null

    @Column
    @NotNull
    var inductionTablaLamina: Double? = null

    @Column
    @NotNull
    var inductionIngresoManual: Double? = null

    @NotNull
    @Column
    var cantidadAceite: Int? = null

    @NotNull
    @Column
    var tipoAceite: String? = null

    @NotBlank
    @Column
    var tipoTransformador: String? = null
}