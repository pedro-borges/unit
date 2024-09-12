package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.ElectricCurrentAmount

class Ampere(amount: Number,
             magnitude: Magnitude = UNI)
    : ElectricCurrentAmount<Unit<ElectricCurrent>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = AMPERE)
{
    open class AmpereUnit(magnitude: Magnitude)
        : Unit<ElectricCurrent>(ElectricCurrent::class, "m", 1.toBigDecimal(), magnitude)

    object AMPERE : AmpereUnit(UNI)
}
