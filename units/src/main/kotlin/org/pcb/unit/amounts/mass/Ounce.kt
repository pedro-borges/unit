package org.pcb.unit.amounts.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

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
