package org.pcb.unit.amounts.time

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

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
