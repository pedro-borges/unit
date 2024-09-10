package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Inch(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = INCH)
{
    object INCH : Unit<Distance>(Distance::class)
    {
        override val symbol = "in"
        override val scale = 0.0254.toBigBigDecimal()
    }
}