package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class League(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = LEAGUE)
{
    object LEAGUE : Unit<Distance>(Distance::class)
    {
        override val symbol = "lea"
        override val scale = 4828.032.toBigBigDecimal()
    }
}