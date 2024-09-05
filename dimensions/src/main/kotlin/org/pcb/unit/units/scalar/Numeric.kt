package org.pcb.unit.units.scalar

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount

class Numeric(amount: Number)
    : ScalarAmount<Unit<Scalar>>(
    amount = amount,
    unit = ONE)
{
    object ONE : NUMBER(1)

    open class NUMBER(scale: Number) : Unit<Scalar>(Scalar::class)
    {
        override val symbol = ""
        override val scale = scale.toBigBigDecimal()
    }
}