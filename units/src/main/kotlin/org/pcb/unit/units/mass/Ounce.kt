package org.pcb.unit.units.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.MassAmount

class Ounce(amount: Number,
            magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = OUNCE)
{
    open class OunceUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "oz", 0.028349523125.toBigDecimal(), magnitude)

    object OUNCE : OunceUnit(UNI)
}
