package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.toBigDecimal

open class ScalarAmount<S : Unit<Scalar>>(
        amount: Number,
        unit: S)
    : Amount<Scalar>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Scalar::class,
                         units = mapOf(unit to 1)))
{
    val scalar = unit

    $operators
}
