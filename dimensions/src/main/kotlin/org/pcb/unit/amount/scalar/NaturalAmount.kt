package org.pcb.unit.amount.scalar

import org.pcb.unit.Unit
import org.pcb.unit.amount.ScalarAmount
import org.pcb.unit.amount.scalar.NaturalAmount.NATURAL
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal

class NaturalAmount(amount: Number)
    : ScalarAmount<NATURAL>(amount, NATURAL)
{
    object NATURAL : Unit<Scalar>(Scalar::class)
    {
        override val symbol = ""
        override val scale = 1.toBigBigDecimal()
    }
}