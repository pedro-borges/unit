package org.pcb.unit.units.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Minute(amount: Number,
             magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount * magnitude.scale,
    unit = MINUTE)
{
    open class MinuteUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "min", magnitude, 60.toBigBigDecimal())

    object MINUTE : MinuteUnit(UNI)
}
