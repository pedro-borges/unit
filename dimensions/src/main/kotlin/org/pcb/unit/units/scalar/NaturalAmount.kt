package org.pcb.unit.units.scalar

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.scalar.NaturalAmount.NATURAL

class NaturalAmount(amount: Number)
    : ScalarAmount<NATURAL>(amount, NATURAL)
{
    object NATURAL : Unit<Scalar>(Scalar::class)
    {
        override val symbol = ""
        override val scale = 1.toBigBigDecimal()
    }
}