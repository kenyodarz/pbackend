package com.cdmtransformadores.produccion.shared

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.util.function.Consumer
import javax.validation.Valid

@RestController
abstract class GenericRestController<T, ID : Serializable>(val serviceAPI: GenericServiceAPI<T, ID>) {

    fun validar(result: BindingResult): ResponseEntity<*> {
        val errors: MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach(Consumer { err: FieldError ->
            errors[err.field] = "El Campo ${err.field} ${err.defaultMessage}"
        })
        return ResponseEntity.badRequest().body<Map<String, Any>>(errors)
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): ResponseEntity<List<T>> {
        return ResponseEntity.ok(serviceAPI.getAll())
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getOne(@PathVariable id: ID): ResponseEntity<*> {
        return when (val entity: T? = serviceAPI.getOne(id)) {
            null -> ResponseEntity.notFound().build<Any>()
            else -> ResponseEntity.ok(entity)
        }
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity: T, result: BindingResult): ResponseEntity<*> {
        if (result.hasErrors()) validar(result)
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAPI.save(entity))
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: ID): ResponseEntity<Any> {
        return when (val entity: T? = serviceAPI.getOne(id)) {
            null -> ResponseEntity.notFound().build<Any>()
            else -> {
                serviceAPI.delete(id)
                ResponseEntity.ok().body(entity)
            }
        }
    }

}