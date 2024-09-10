package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Hand(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = HAND)
{
    object HAND : Unit<Distance>(Distance::class)
    {
        override val symbol = "hh"
        override val scale = 0.1016.toBigBigDecimal()
    }
}