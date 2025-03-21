package org.pcb.unit.amounts.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

class Dalton(amount: Number,
             magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = DALTON)
{
    open class DaltonUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "da", 1.16605390689252.toBigDecimal().movePointLeft(27), magnitude)

    object DALTON : DaltonUnit(UNI)
}
