package org.pcb.unit.units.scalar

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.scalar.PercentAmount.PERCENT

@Suppress("unused")
class PercentAmount(amount: Number)
    : ScalarAmount<PERCENT>(amount, PERCENT)
{
    object PERCENT : Unit<Scalar>(Scalar::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "%"
        override val scale = 0.01.toBigBigDecimal()
    }
}