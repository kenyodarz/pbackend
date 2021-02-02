package com.cdmtransformadores.produccion.shared

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

@Service
abstract class GenericServiceImpl<T, ID : Serializable> : GenericServiceAPI<T, ID> {

    override fun getAll(): List<T> {
        val returnList: MutableList<T> = ArrayList()
        getRepository().findAll().forEach(Consumer(returnList::add))
        return returnList
    }

    override fun getOne(id: ID): T? {
        // Obtenemos un Optional De Java8
        val optional: Optional<T> = getRepository().findById(id)
        // Retornamos null en caso de que no hallemos el objeto
        return optional.orElse(null)
    }

    override fun save(entity: T): T {
        return getRepository().save(entity!!)
    }

    override fun delete(id: ID) {
        return getRepository().deleteById(id)
    }

    abstract override fun getRepository(): JpaRepository<T, ID>
}