package org.pcb.unit.units.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.MassAmount

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
