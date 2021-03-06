package no.hvl.dat251.fjelltur.dto

import no.hvl.dat251.fjelltur.entity.Account

data class AccountCreationRequest(
  val username: String,
  val password: String,
  val photoUrl: String?,
)

data class LoginRequest(
  val username: String,
  val password: String,
)

data class RegisteredAccountResponse(
  val id: String,
  val username: String,
  val photoUrl: String,
  val score: Float,
  val disabled: Boolean,
  val permissions: Set<String>
)

data class UpdatePasswordRequest(
  val oldPassword: String,
  val newPassword: String,
)

/**
 * Represents the Id of an account
 */
inline class AccountId(val id: String)

fun Account.toResponse(): RegisteredAccountResponse = RegisteredAccountResponse(
  id.id,
  username,
  photoUrl ?: "",
  score,
  disabled,
  authorities
)
