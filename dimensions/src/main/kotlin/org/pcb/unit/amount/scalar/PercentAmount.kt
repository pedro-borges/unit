package org.pcb.unit.amount.scalar

import org.pcb.unit.Unit
import org.pcb.unit.amount.ScalarAmount
import org.pcb.unit.amount.scalar.PercentAmount.PERCENT
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class PercentAmount(amount: Number)
    : ScalarAmount<PERCENT>(amount, PERCENT)
{
    object PERCENT : Unit<Scalar>(Scalar::class)
    {
        override val symbol = "%"
        override val scale = 0.01.toBigBigDecimal()
    }
}