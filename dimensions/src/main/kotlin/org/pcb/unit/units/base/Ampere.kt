package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricCurrentAmount

class Ampere(amount: Number,
             magnitude: Magnitude = UNI)
    : ElectricCurrentAmount<Unit<ElectricCurrent>>(
    amount = amount * magnitude.scale,
    unit = AMPERE)
{
    open class AmpereUnit(magnitude: Magnitude)
        : Unit<ElectricCurrent>(ElectricCurrent::class, "m", magnitude, 1.toBigBigDecimal())

    object AMPERE : AmpereUnit(UNI)
}


