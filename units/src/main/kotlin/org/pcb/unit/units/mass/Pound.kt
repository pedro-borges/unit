package org.pcb.units.amount.mass

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Pound(amount: Number)
    : MassAmount<Unit<Mass>>(
    amount = amount,
    unit = POUND)
{
    object POUND : Unit<Mass>(Mass::class, "lb", 0.45359237.toBigBigDecimal())
}