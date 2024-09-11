package org.pcb.unit.units.scalar

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount

class Numeric(amount: Number,
              magnitude: Magnitude = UNI)
    : ScalarAmount<Unit<Scalar>>(
    amount = amount,
    unit = NumericUnit(magnitude))
{
    open class NumericUnit(magnitude: Magnitude)
        : Unit<Scalar>(Scalar::class, magnitude.symbol, magnitude, 1.toBigBigDecimal())

    object NUMERIC : NumericUnit(UNI)
}

