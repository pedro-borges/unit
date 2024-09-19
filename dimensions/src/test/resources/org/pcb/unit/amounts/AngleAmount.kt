package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.toBigDecimal

open class AngleAmount<A : Unit<Angle>>(
        amount: Number,
        unit: A)
    : Amount<Angle>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Angle::class,
                        units = mapOf(unit to 1)))
{
    val angle = unit

    $operators
}