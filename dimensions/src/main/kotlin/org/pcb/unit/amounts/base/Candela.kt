package org.pcb.unit.amounts.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.LuminousIntensityAmount

class Candela(amount: Number,
              magnitude: Magnitude = UNI)
    : LuminousIntensityAmount<Unit<LuminousIntensity>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = CANDELA)
{
    open class CandelaUnit(magnitude: Magnitude)
        : Unit<LuminousIntensity>(LuminousIntensity::class, "cd", 1.toBigDecimal(), magnitude)

    object CANDELA : CandelaUnit(UNI)
}
