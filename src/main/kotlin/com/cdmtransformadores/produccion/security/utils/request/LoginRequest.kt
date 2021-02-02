package com.cdmtransformadores.produccion.security.utils.request

import javax.validation.constraints.NotBlank


class LoginRequest {
    @NotBlank
    var username: String? = null

    @NotBlank
    var password: String? = null
}