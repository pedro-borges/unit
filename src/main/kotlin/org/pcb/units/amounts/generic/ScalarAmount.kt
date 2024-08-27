package org.pcb.units.amounts.generic

import org.pcb.units.domain.Scalar
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit

open class ScalarAmount(amount: Number)
    : Amount<Scalar>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         units = mapOf(),
                         domain = Scalar::class))
