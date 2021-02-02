package com.cdmtransformadores.produccion.security.controllers

import com.cdmtransformadores.produccion.security.keys.JwtUtils
import com.cdmtransformadores.produccion.security.models.ERole
import com.cdmtransformadores.produccion.security.models.Role
import com.cdmtransformadores.produccion.security.models.User
import com.cdmtransformadores.produccion.security.repositories.RoleRepository
import com.cdmtransformadores.produccion.security.repositories.UserRepository
import com.cdmtransformadores.produccion.security.services.UserDetailsImpl
import com.cdmtransformadores.produccion.security.utils.messages.JwtResponse
import com.cdmtransformadores.produccion.security.utils.messages.MessageResponse
import com.cdmtransformadores.produccion.security.utils.request.LoginRequest
import com.cdmtransformadores.produccion.security.utils.request.SignupRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid


@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/auth")
class AuthRestController {

    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var roleRepository: RoleRepository? = null

    @Autowired
    var encoder: PasswordEncoder? = null

    @Autowired
    var jwtUtils: JwtUtils? = null

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest, result: BindingResult): ResponseEntity<Any> {
        // Validamos los Campos
        if (result.hasErrors()) return this.validar(result)
        val authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username!!, loginRequest.password!!))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils!!.generateJwtToken(authentication)
        val userDetails = authentication!!.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())
        return ResponseEntity.ok(JwtResponse(
            jwt,
            userDetails.id!!,
            userDetails.username,
            userDetails.name!!,
            userDetails.email!!,
            roles)
        )

    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signUpRequest: SignupRequest, result: BindingResult): ResponseEntity<Any> {
        // Validamos los Campos
        if (result.hasErrors()) return this.validar(result)
        if (userRepository!!.existsByUsername(signUpRequest.username!!)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Este nombre de usuario ya existe!"))
        }
        if (userRepository!!.existsByEmail(signUpRequest.email!!)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Este Email ya esta en uso!"))
        }
        // Creación de un Nuevo Usuario
        val user = User(
            signUpRequest.username!!,
            signUpRequest.name!!,
            signUpRequest.email!!,
            encoder!!.encode(signUpRequest.password)
        )
        val strRoles: MutableSet<String>? = signUpRequest.roles
        val roles: MutableSet<Role> = HashSet()
        if (strRoles == null) {
            val userRole = roleRepository!!.findByName(ERole.ROLE_USER)
                .orElseThrow { RuntimeException("Error: Rol user no Encontrado.") }
            roles.add(userRole)
        } else {
            strRoles.forEach { role ->
                when (role) {
                    "admin" -> {
                        val adminRole = roleRepository!!.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow { RuntimeException("Error: Rol admin no Encontrado.") }
                        roles.add(adminRole)
                    }
                    "mod" -> {
                        val modRole = roleRepository!!.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow { RuntimeException("Error: Rol mod no Encontrado.") }
                        roles.add(modRole)
                    }
                    "sup" -> {
                        val supRole = roleRepository!!.findByName(ERole.ROLE_SUPERVISOR)
                            .orElseThrow { RuntimeException("EError: Rol sup no Encontrado.") }
                        roles.add(supRole)
                    }
                    else -> {
                        val userRole = roleRepository!!.findByName(ERole.ROLE_USER)
                            .orElseThrow { RuntimeException("Error: Rol user no Encontrado.") }
                        roles.add(userRole)
                    }
                }
            }
        }
        user.roles = roles
        userRepository!!.save(user)
        return ResponseEntity.ok(MessageResponse("Usuario Registrado Con éxito"))
    }

    fun validar(result: BindingResult): ResponseEntity<Any> {
        val errores: MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach { err ->
            run {
                errores[err.field] = "El Campo ${err.field} ${err.defaultMessage}"
            }
        }
        return ResponseEntity.badRequest().body(errores)
    }

}