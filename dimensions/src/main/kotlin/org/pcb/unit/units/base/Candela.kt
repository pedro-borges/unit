package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.LuminousIntensity
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LuminousIntensityAmount

class Candela(amount: Number,
              magnitude: Magnitude = UNI)
    : LuminousIntensityAmount<Unit<LuminousIntensity>>(
    amount = amount * magnitude.scale,
    unit = CANDELA)
{
    open class CandelaUnit(magnitude: Magnitude)
        : Unit<LuminousIntensity>(LuminousIntensity::class, "cd", magnitude, 1.toBigBigDecimal())

    object CANDELA : CandelaUnit(UNI)
}

