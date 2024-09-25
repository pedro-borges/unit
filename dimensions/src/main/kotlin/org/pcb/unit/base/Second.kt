package org.pcb.unit.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

class Second(amount: Number,
             magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = SECOND)
{
    open class SecondUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "s", 1.toBigDecimal(), magnitude)

    object SECOND : SecondUnit(UNI)
}
