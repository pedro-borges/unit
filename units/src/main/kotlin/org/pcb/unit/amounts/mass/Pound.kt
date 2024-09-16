package org.pcb.unit.amounts.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

class Pound(amount: Number,
            magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = POUND)
{
    open class PoundUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "lb", 0.45359237.toBigDecimal(), magnitude)

    object POUND : PoundUnit(UNI)
}
