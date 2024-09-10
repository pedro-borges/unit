package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Furlong(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = FURLONG)
{
    object FURLONG : Unit<Distance>(Distance::class)
    {
        override val symbol = "fur"
        override val scale = 201.168.toBigBigDecimal()
    }
}