package org.pcb.unit.amounts.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal

class Meter(amount: Number,
            magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = METER)
{
    open class MeterUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "m", 1.toBigDecimal(), magnitude)

    object METER : MeterUnit(UNI)
}
