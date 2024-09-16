package org.pcb.unit.amounts.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

class Hour(amount: Number,
           magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = HOUR)
{
    open class HourUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "h", 3600.toBigDecimal(), magnitude)

    object HOUR : HourUnit(UNI)
}
