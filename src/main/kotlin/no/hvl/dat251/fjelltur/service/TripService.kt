package no.hvl.dat251.fjelltur.service

import no.hvl.dat251.fjelltur.dto.GPSLocationRequest
import no.hvl.dat251.fjelltur.dto.TripId
import no.hvl.dat251.fjelltur.entity.Account
import no.hvl.dat251.fjelltur.entity.GPSLocation
import no.hvl.dat251.fjelltur.entity.Rule
import no.hvl.dat251.fjelltur.entity.Trip
import no.hvl.dat251.fjelltur.exception.AccountAlreadyOnTripException
import no.hvl.dat251.fjelltur.exception.AccountNotFoundException
import no.hvl.dat251.fjelltur.exception.NoCurrentTripException
import no.hvl.dat251.fjelltur.exception.TripNotFoundException
import no.hvl.dat251.fjelltur.exception.TripNotOngoingException
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Duration

interface TripService {

  @ExceptionHandler(AccountNotFoundException::class, AccountAlreadyOnTripException::class)
  fun startTrip(request: GPSLocationRequest): Trip

  @ExceptionHandler(TripNotOngoingException::class)
  fun endTrip(trip: Trip): Trip

  @ExceptionHandler(TripNotOngoingException::class)
  fun addGPSLocation(trip: Trip, location: GPSLocation): Trip

  @ExceptionHandler(TripNotFoundException::class)
  fun findTrip(id: TripId): Trip

  fun findTripOrNull(id: TripId): Trip?

  /**
   * Find the current trip the logged in user is on, if any
   */
  fun currentTripOrNull(): Trip?

  /**
   * Find the current trip the given user is on, if any
   */
  fun currentTripOrNull(account: Account): Trip?

  /**
   * Find the current trip the logged in user is on, if any
   */
  @ExceptionHandler(NoCurrentTripException::class)
  fun currentTrip(): Trip

  /**
   * Find the current trip the given user is on, if any
   */
  @ExceptionHandler(NoCurrentTripException::class)
  fun currentTrip(account: Account): Trip

  /**
   * Calculate the current length of the trip
   */
  fun calculateTripDuration(trip: Trip): Duration

  /**
   * Calculate distance traveled in the trip
   */
  fun calculateTripDistance(trip: Trip): Int

  fun tripScore(trip: Trip): Pair<Rule?, Int>

  /**
   * Return all trips from a user that is not ongoing
   */
  fun findPreviousTrips(id: Account): List<Trip>
}
