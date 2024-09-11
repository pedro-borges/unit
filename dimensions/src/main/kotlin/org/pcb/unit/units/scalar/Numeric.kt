package org.pcb.unit.units.scalar

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount

class Numeric(amount: Number)
    : ScalarAmount<Unit<Scalar>>(
    amount = amount,
    unit = NUMERIC)
{
    object NUMERIC : Unit<Scalar>(Scalar::class, "", 1.toBigBigDecimal())
}