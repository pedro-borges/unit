package org.pcb.unit.units.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Hour(amount: Number,
           magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = HourUnit(magnitude))
{
    open class HourUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "h", magnitude, 3600.toBigBigDecimal())

    object HOUR : HourUnit(UNI)
}