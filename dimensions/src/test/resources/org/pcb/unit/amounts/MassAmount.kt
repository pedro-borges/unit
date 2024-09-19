package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

open class MassAmount<M : Unit<Mass>>(
        amount: Number,
        unit: M)
    : Amount<Mass>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = mapOf(unit to 1)))
{
    val mass = unit

    $operators
}
