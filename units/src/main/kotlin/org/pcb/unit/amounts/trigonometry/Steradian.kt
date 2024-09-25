package org.pcb.unit.amounts.trigonometry

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.SolidAngleAmount
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.SolidAngle
import org.pcb.unit.toBigDecimal

class Steradian(amount: Number,
                magnitude: Magnitude = UNI)
    : SolidAngleAmount<Unit<Angle>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = RADIAN)
{
    open class SteradianUnit(magnitude: Magnitude)
        : Unit<SolidAngle>(SolidAngle::class, "°", 1.toBigDecimal(), magnitude)

    object STERADIAN : SteradianUnit(UNI)
}
