package no.hvl.dat251.fjelltur.exception

import no.hvl.dat251.fjelltur.dto.TripId
import no.hvl.dat251.fjelltur.entity.Account
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class TripNotFoundException(id: TripId) : RuntimeException("Failed to find a trip with the $id")

@ResponseStatus(HttpStatus.LOCKED)
class TripNotOngoingException(id: TripId) : RuntimeException("Trip '$id' is not ongoing")

@ResponseStatus(HttpStatus.NOT_FOUND)
class NoCurrentTripException(account: Account) : RuntimeException("The account ${account.username} is not currently on a trip")

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class AccountAlreadyOnTripException(account: Account) : RuntimeException("The user ${account.username} (id ${account.id}) is already on a trip")

@ResponseStatus(HttpStatus.CONFLICT)
class TooManyOngoingTripsException(account: Account) :
  RuntimeException("THe user ${account.username} have multiple trips ongoing at the same time. Please close one or more of them.")
