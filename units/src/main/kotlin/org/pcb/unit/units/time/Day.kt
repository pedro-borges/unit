package org.pcb.unit.units.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.TimeAmount

class Day(amount: Number,
          magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = DAY)
{
    open class DayUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "D", 86400.toBigDecimal(), magnitude)

    object DAY : DayUnit(UNI)
}
