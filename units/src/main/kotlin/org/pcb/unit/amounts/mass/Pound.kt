package org.pcb.unit.amounts.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.MassAmount

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
