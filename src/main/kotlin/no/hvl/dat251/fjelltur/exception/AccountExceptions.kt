package no.hvl.dat251.fjelltur.exception

import no.hvl.dat251.fjelltur.dto.AccountId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Elg
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class NotUniqueAccountEmailException : RuntimeException("An account with the given email already exists")

@ResponseStatus(HttpStatus.NOT_FOUND)
class AccountNotFoundException(id: AccountId) : RuntimeException("Failed to find an account with $id")

@ResponseStatus(HttpStatus.FORBIDDEN)
class NotLoggedInException : RuntimeException("You must be logged to use this endpoint")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class MissingFieldException(field: String) : RuntimeException("Blank or missing required field: $field")

@ResponseStatus(HttpStatus.FORBIDDEN)
class InsufficientAccessException(to: String) : RuntimeException("You are not allowed to $to")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AccountUpdateFailedException(message: String?) : RuntimeException(message)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AccountCreationFailedException(message: String?) : RuntimeException(message)

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
class PasswordNotSecureException(message: String?) : RuntimeException(message)
