package org.pcb.units.amount.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Pound(amount: Number,
            magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount,
    unit = PoundUnit(magnitude))
{
    open class PoundUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, magnitude.symbol + "lb", magnitude.scale * 0.45359237.toBigBigDecimal())

    object POUND : PoundUnit(UNI)
}