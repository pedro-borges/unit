package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricCurrentAmount

class Ampere(amount: Number)
    : ElectricCurrentAmount<Unit<ElectricCurrent>>(
    amount = amount,
    unit = AMPERE)
{
    object AMPERE : Unit<ElectricCurrent>(ElectricCurrent::class)
    {
        override val symbol = "A"
        override val scale = 1.toBigBigDecimal()
    }
}
