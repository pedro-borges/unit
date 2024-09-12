package org.pcb.unit.units.mass

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Stone(amount: Number,
            magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount * magnitude.scale,
    unit = STONE)
{
    open class StoneUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "st", magnitude, 6.35029318.toBigBigDecimal())

    object STONE : StoneUnit(UNI)
}
