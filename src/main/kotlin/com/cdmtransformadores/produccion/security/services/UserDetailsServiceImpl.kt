package com.cdmtransformadores.produccion.security.services

import com.cdmtransformadores.produccion.security.models.User
import com.cdmtransformadores.produccion.security.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(val repository: UserRepository) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = repository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("No se ha encontrado el usuario: $username") }
        return UserDetailsImpl.build(user)
    }
}