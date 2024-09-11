package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Chain(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = CHAIN)
{
    object CHAIN : Unit<Distance>(Distance::class, "ch", 20.1168.toBigBigDecimal())
}