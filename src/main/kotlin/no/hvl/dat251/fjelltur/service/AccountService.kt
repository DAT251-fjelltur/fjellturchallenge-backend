package no.hvl.dat251.fjelltur.service

import no.hvl.dat251.fjelltur.ADMIN_ROLE
import no.hvl.dat251.fjelltur.DELETE_OTHER_PERMISSION
import no.hvl.dat251.fjelltur.GET_OTHER_PERMISSION
import no.hvl.dat251.fjelltur.UPDATE_OTHER_USER_PERMISSION
import no.hvl.dat251.fjelltur.dto.AccountCreationRequest
import no.hvl.dat251.fjelltur.dto.AccountId
import no.hvl.dat251.fjelltur.exception.AccountCreationFailedException
import no.hvl.dat251.fjelltur.exception.AccountNotFoundException
import no.hvl.dat251.fjelltur.exception.NotLoggedInException
import no.hvl.dat251.fjelltur.model.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.ExceptionHandler

interface AccountService {

  @ExceptionHandler(AccountCreationFailedException::class)
  fun createAccount(request: AccountCreationRequest): Account

  @ExceptionHandler(NotLoggedInException::class)
  fun getCurrentAccount(): Account

  fun getCurrentAccountOrNull(): Account?

  @get:ExceptionHandler(AccountNotFoundException::class)
  val loggedInUid: AccountId

  val loggedInUidOrNull: AccountId?

  val isLoggedIn: Boolean

  val isNotLoggedIn: Boolean

  fun getAccountByUsername(username: String): Account

  fun getAccountByUsernameOrNull(username: String): Account?

  fun getAccountByUid(uid: AccountId): Account

  fun getAccountByUidOrNull(uid: AccountId): Account?

  @PreAuthorize("hasAuthority('$GET_OTHER_PERMISSION') or hasRole('$ADMIN_ROLE')")
  fun findAllByDisabled(disabled: Boolean, pageable: Pageable): Page<Account>

  @PreAuthorize("hasAuthority('$GET_OTHER_PERMISSION') or hasRole('$ADMIN_ROLE')")
  fun findAll(pageable: Pageable): Page<Account>

  @PreAuthorize("hasAuthority('$UPDATE_OTHER_USER_PERMISSION') or hasRole('$ADMIN_ROLE')")
  fun updateUser(user: Account): Account

  /**
   * Remove the user with the given login name from the system.
   */
  @PreAuthorize("hasAuthority('$DELETE_OTHER_PERMISSION') or hasRole('$ADMIN_ROLE')")
  fun deleteUser(username: String)

  /**
   * Modify the current user's password. This should change the user's password in the
   * persistent user repository (database, LDAP etc).
   *
   * @param oldPassword current password (for re-authentication)
   * @param newPassword the password to change to
   */
  fun changePassword(oldPassword: String, newPassword: String): Account

  /**
   * Check if a user with the supplied login name exists in the system.
   */
  fun userExists(username: String): Boolean
}
