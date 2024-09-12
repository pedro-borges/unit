package org.pcb.unit.units.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.TimeAmount

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
