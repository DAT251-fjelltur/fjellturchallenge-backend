package no.hvl.dat251.fjelltur.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Mathias
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NotUniqueRuleException(name: String) : RuntimeException("There is already a rule with the name: '$name'")

@ResponseStatus(HttpStatus.NOT_FOUND)
class UnknownRuleNameException(name: String) : RuntimeException("There is no rule with that name")
