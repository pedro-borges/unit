package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Mile(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = MILE)
{
    object MILE : Unit<Distance>(Distance::class, "mi", 1609.344.toBigBigDecimal())
}