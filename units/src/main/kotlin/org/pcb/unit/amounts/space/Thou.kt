package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal

class Thou(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = THOU)
{
    open class ThouUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "th", 0.0000254.toBigDecimal(), magnitude)

    object THOU : ThouUnit(UNI)
}
