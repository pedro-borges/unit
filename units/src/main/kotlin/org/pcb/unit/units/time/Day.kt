package org.pcb.unit.units.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Day(amount: Number,
          magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = DayUnit(magnitude))
{
    open class DayUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "D", magnitude, 86400.toBigBigDecimal())

    object DAY : DayUnit(UNI)
}