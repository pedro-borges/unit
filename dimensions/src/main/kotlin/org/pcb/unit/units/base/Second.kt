package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.TimeAmount

class Second(amount: Number,
             magnitude: Magnitude = UNI)
    : TimeAmount<Unit<Time>>(
    amount = amount,
    unit = SecondUnit(magnitude))
{
    open class SecondUnit(magnitude: Magnitude) : Unit<Time>(Time::class, magnitude.symbol + "s", magnitude.scale)

    object SECOND : SecondUnit(UNI)
}

