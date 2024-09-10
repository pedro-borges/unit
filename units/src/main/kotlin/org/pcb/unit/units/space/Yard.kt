package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Yard(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = YARD)
{
    object YARD : Unit<Distance>(Distance::class)
    {
        override val symbol = "yd"
        override val scale = 0.9144.toBigBigDecimal()
    }
}