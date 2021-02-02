package com.cdmtransformadores.produccion.models

import com.cdmtransformadores.produccion.security.models.User
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "orden_de_produccion")
class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProductionOrder: Long? = null

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
    var design: Design? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orden_de_produccion_transformadores",
        joinColumns = [JoinColumn(name = "id_production_order")],
        inverseJoinColumns = [JoinColumn(name = "numero_serie")]
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