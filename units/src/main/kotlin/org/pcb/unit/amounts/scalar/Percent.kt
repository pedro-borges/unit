package org.pcb.unit.amounts.scalar

import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.ScalarAmount
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.toBigDecimal

class Percent(amount: Number)
    : ScalarAmount<Unit<Scalar>>(
    amount = amount,
    unit = PERCENT)
{
    object PERCENT
        : Unit<Scalar>(Scalar::class, "%", 0.01.toBigDecimal(), UNI)
}
