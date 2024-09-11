package org.pcb.unit.units.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Ounce(amount: Number,
            magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount,
    unit = OunceUnit(magnitude))
{
    open class OunceUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "oz", magnitude, 0.028349523125.toBigBigDecimal())

    object OUNCE : OunceUnit(UNI)
}