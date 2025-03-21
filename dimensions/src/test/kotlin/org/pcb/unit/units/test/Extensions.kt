package org.pcb.unit.units.test

import java.math.BigDecimal.ZERO
import org.junit.jupiter.api.Assertions.assertEquals
import org.pcb.unit.Amount
import org.pcb.unit.toBigDecimal

fun assertEquals(expected: Number, actual: Number) = assertEquals(expected.toBigDecimal(), actual.toBigDecimal())
fun assertZero(actual: Number) = assertEquals(ZERO, actual.toBigDecimal())
fun assertZero(actual: Amount<*>) = assertZero(actual.amount)