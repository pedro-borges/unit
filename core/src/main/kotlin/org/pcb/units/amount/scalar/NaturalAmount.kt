package org.pcb.units.amount.scalar

import org.pcb.units.Unit
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.amount.scalar.NaturalAmount.NATURAL
import org.pcb.units.domain.Scalar
import org.pcb.units.toBigBigDecimal

class NaturalAmount(amount: Number)
    : ScalarAmount<NATURAL>(amount, NATURAL)
{
    object NATURAL : Unit<Scalar>(Scalar::class)
    {
        override val symbol = ""
        override val cannon = 1.toBigBigDecimal()
    }
}