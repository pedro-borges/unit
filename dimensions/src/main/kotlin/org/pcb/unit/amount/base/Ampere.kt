package org.pcb.unit.amount.base

import org.pcb.unit.Unit
import org.pcb.unit.amount.ElectricCurrentAmount
import org.pcb.unit.amount.base.Ampere.AMPERE
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Ampere(amount: Number)
    : ElectricCurrentAmount<AMPERE>(amount, AMPERE)
{
    object AMPERE : Unit<ElectricCurrent>(ElectricCurrent::class)
    {
        override val symbol = "A"
        override val scale = 1.toBigBigDecimal()
    }
}
