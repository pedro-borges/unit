package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.base.Ampere.AMPERE

@Suppress("unused")
class Ampere(amount: Number)
    : ElectricCurrentAmount<AMPERE>(amount, AMPERE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object AMPERE : Unit<ElectricCurrent>(ElectricCurrent::class)
    {
        override val symbol = "A"
        override val scale = 1.toBigBigDecimal()
    }
}
