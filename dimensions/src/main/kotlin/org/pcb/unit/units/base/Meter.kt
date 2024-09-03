package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.base.Meter.METER

@Suppress("unused")
class Meter(amount: Number)
    : LengthAmount<METER>(amount.toBigBigDecimal(), METER)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object METER : Unit<Length>(Length::class)
    {
        override val symbol = "m"
        override val scale = 1.toBigBigDecimal()
    }
}