package org.pcb.unit.amounts.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.TimeAmount

class Minute(amount: Number,
             magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = MINUTE)
{
    open class MinuteUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "min", 60.toBigDecimal(), magnitude)

    object MINUTE : MinuteUnit(UNI)
}
