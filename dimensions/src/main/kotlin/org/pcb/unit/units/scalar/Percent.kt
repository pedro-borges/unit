package org.pcb.unit.units.scalar

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount

class Percent(amount: Number)
    : ScalarAmount<Unit<Scalar>>(
    amount = amount,
    unit = PERCENT)
{
    object PERCENT : Unit<Scalar>(Scalar::class, "%", 0.01.toBigBigDecimal())
}