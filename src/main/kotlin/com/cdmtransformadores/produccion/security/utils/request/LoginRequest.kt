package com.cdmtransformadores.produccion.security.utils.request

import javax.validation.constraints.NotBlank


class LoginRequest(@field:NotBlank var username: String, @field:NotBlank var password: String)