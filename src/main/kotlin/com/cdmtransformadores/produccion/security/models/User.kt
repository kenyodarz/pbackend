package com.cdmtransformadores.produccion.security.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(
    name = "users",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username", "email"))]
)
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank
    @Size(max = 20)
    var username: String? = null

    @NotBlank
    var name: String? = null

    @NotBlank
    @Size(max = 100)
    @Email
    var email: String? = null

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    var password: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = HashSet()

    constructor()

    constructor(username: String, name: String, email: String, password: String) {
        this.username = username
        this.name = name
        this.email = email
        this.password = password
    }

}