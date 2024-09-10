package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class DeciMeter(amount: Number)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = DECIMETER)
{
    object DECIMETER : Unit<Distance>(Distance::class)
    {
        override val symbol = "dm"
        override val scale = 0.1.toBigBigDecimal()
    }
}