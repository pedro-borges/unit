package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.domain.Scalar
import org.pcb.units.toBigBigDecimal

open class ScalarAmount<S : Unit<Scalar>>(amount: Number,
                                          val scalar: S)
    : Amount<Scalar>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Scalar::class,
                         units = listOf()))
