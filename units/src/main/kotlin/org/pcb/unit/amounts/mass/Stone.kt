package org.pcb.unit.amounts.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

class Stone(amount: Number,
            magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = STONE)
{
    open class StoneUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "st", 6.35029318.toBigDecimal(), magnitude)

    object STONE : StoneUnit(UNI)
}
