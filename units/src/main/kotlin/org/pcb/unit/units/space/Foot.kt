package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Foot(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = FOOT)
{
    object FOOT : Unit<Distance>(Distance::class, "ft", 0.3048.toBigBigDecimal())
}