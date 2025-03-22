package org.pcb.unit.amounts.trigonometry

import java.math.BigDecimal
import org.pcb.unit.Constants
import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AngleAmount
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.div
import org.pcb.unit.toBigDecimal

class Degree(amount: Number,
             magnitude: Magnitude = UNI)
    : AngleAmount<Unit<Angle>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = DEGREE)
{
    open class DegreeUnit(magnitude: Magnitude)
        : Unit<Angle>(Angle::class, "°", Constants.PI / BigDecimal(180), magnitude)

    object DEGREE : DegreeUnit(UNI)
}
