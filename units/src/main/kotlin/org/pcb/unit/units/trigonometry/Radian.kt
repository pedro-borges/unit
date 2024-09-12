package org.pcb.unit.units.trigonometry

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Angle
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.AngleAmount

class Radian(amount: Number,
             magnitude: Magnitude = UNI)
    : AngleAmount<Unit<Angle>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = RADIAN)
{
    open class RadianUnit(magnitude: Magnitude)
        : Unit<Angle>(Angle::class, "rad", 1.toBigDecimal(), magnitude)

    object RADIAN : RadianUnit(UNI)
}
