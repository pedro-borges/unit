package org.pcb.unit.units.mass

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.mass.Ounce.OUNCE

@Suppress("unused")
class Ounce(amount: Number)
    : MassAmount<OUNCE>(amount.toBigBigDecimal(), OUNCE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object OUNCE : Unit<Mass>(Mass::class)
    {
        override val symbol = "oz"
        override val scale = 0.028349523125.toBigBigDecimal()
    }
}