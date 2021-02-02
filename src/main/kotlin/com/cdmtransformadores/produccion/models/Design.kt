package com.cdmtransformadores.produccion.models

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class Design {
    @Id
    var id: String?=null

    @Column
    var description:String?=null

    @Column
    var fecha: LocalDate?=null
}