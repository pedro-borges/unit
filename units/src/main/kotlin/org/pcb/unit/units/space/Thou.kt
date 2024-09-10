package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Thou(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = THOU)
{
    object THOU : Unit<Distance>(Distance::class)
    {
        override val symbol = "th"
        override val scale = 0.0000254.toBigBigDecimal()
    }
}