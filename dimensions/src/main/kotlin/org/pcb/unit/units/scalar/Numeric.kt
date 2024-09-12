package org.pcb.unit.units.scalar

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.ScalarAmount

class Numeric(amount: Number,
              magnitude: Magnitude = UNI)
    : ScalarAmount<Unit<Scalar>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = NUMERIC)
{
    open class NumericUnit(magnitude: Magnitude)
        : Unit<Scalar>(Scalar::class, magnitude.symbol, 1.toBigDecimal(), magnitude)

    object NUMERIC : NumericUnit(UNI)
}
