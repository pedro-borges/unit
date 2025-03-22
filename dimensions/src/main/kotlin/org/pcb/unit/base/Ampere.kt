package org.pcb.unit.base

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.ElectricCurrentAmount
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.toBigDecimal

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
