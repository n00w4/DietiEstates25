package it.unina.dietiestates.data.dto

data class ChangeAdminPwdForm(
    val oldPassword: String,
    val newPassword: String,
    val email: String,
    val partitaIVA: String
)
