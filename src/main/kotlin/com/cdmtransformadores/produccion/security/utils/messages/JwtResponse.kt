package com.cdmtransformadores.produccion.security.utils.messages

class JwtResponse (
    val accessToken: String,
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val roles: List<String>
) {
    val type = "Bearer"
}