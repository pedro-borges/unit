package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.TimeAmount

class Second(amount: Number,
             magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount * magnitude.scale,
    unit = SECOND)
{
    open class SecondUnit(magnitude: Magnitude)
        : Unit<Time>(Time::class, "s", magnitude, 1.toBigBigDecimal())

    object SECOND : SecondUnit(UNI)
}

