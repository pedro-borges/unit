package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.TimeAmount

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
