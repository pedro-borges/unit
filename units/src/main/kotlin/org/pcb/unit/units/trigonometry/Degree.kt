package org.pcb.unit.units.trigonometry

import java.math.BigDecimal
import org.pcb.unit.Constants
import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Angle
import org.pcb.unit.div
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.AngleAmount
import org.pcb.unit.units.trigonometry.Degree.DEGREE

class Degree(amount: Number,
             magnitude: Magnitude = UNI)
    : AngleAmount<DEGREE>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = DEGREE)
{
    open class DegreeUnit(magnitude: Magnitude)
        : Unit<Angle>(Angle::class, "°", Constants.PI / BigDecimal(180), magnitude)

    object DEGREE : DegreeUnit(UNI)
}
