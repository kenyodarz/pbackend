package com.cdmtransformadores.produccion.models

import com.cdmtransformadores.produccion.models.enums.ESection
import javax.persistence.*

@Entity
@Table(name = "section")
class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    var nombre: ESection?=null
}