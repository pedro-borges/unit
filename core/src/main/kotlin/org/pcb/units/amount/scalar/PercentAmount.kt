package org.pcb.units.amount.scalar

import org.pcb.units.Unit
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.amount.scalar.PercentAmount.PERCENT
import org.pcb.units.domain.Scalar
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class PercentAmount(amount: Number)
    : ScalarAmount<PERCENT>(amount, PERCENT)
{
    object PERCENT : Unit<Scalar>(Scalar::class)
    {
        override val symbol = "%"
        override val cannon = 0.01.toBigBigDecimal()
    }
}