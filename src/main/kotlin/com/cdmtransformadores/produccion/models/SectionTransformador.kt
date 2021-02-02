package com.cdmtransformadores.produccion.models

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "section_transformador")
class SectionTransformador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSectionTransformador: Long? = null

    @NotNull
    @OneToOne
    var transformador: Transformador? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "section_transformador_section",
        joinColumns = [JoinColumn(name = "section_transformador_id_section_transformador")],
        inverseJoinColumns = [JoinColumn(name = "section_id")])
    var sections: MutableSet<Section> = HashSet()

    @Column
    @NotNull
    var fecha: LocalDate? = null

    @Column
    var observacion: String? = null

    fun addSection(section: Section) {
        this.sections.add(section)
    }

    fun removeSection(section: Section) {
        this.sections.remove(section)
    }

}