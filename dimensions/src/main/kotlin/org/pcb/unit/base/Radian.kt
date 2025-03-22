package org.pcb.unit.base

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AngleAmount
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.toBigDecimal

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
