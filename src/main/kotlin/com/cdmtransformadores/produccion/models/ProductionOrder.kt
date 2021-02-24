package com.cdmtransformadores.produccion.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "orden_de_produccion")
class ProductionOrder {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var idProductionOrder: String? = null

    @Column
    var numeroOrden: String? = null

    @Column
    @NotBlank
    var description: String? = null

    @Column
    @NotNull
    var cantidad: String?= null

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var createAt: Date? = null

    @Column
    var inProduction: Boolean? = null

    @OneToOne
    @NotNull
    var modelo: Modelo? = null

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "orden_de_produccion_transformadores",
        joinColumns = [JoinColumn(name = "id_production_order")],
        inverseJoinColumns = [JoinColumn(name = "numero_serie_interno")]
    )
    val transformadores: MutableSet<Transformador> = HashSet()

    @PrePersist
    fun prePersist() {
        Date().also { createAt = it }
        true.also { inProduction = it }
    }

    fun addTransformador(t: Transformador) {
        transformadores.add(t)
    }

    fun removeTransformador(t: Transformador) {
        transformadores.remove(t)
    }

}
