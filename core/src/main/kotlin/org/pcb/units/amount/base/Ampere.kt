package org.pcb.units.amount.base

import org.pcb.units.Unit
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.base.Ampere.AMPERE
import org.pcb.units.dimension.ElectricCurrent
import org.pcb.units.toBigBigDecimal

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
