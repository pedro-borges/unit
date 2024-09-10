package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Meter(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigBigDecimal(),
    unit = METER)
{
    object METER : Unit<Distance>(Distance::class)
    {
        override val symbol = "m"
        override val scale = 1.toBigBigDecimal()
    }
}