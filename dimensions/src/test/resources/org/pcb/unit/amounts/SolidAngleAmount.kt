package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.SolidAngle
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SolidAngleAmount<A : Unit<Angle>>(
        amount: Number,
        unit: A)
    : Amount<SolidAngle>(amount = amount.toBigDecimal(),
                         unit = CompositeUnit(
                             domain = SolidAngle::class,
                             units = mapOf(unit to 1)))
{
    val angle = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SolidAngle>) =
            SolidAngleAmount(amount = amount * unit.scale,
                             unit = RADIAN)
    }

    $operators
}
