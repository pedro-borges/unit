package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Inch.INCH
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Inch(amount: Number)
    : LengthAmount<INCH>(amount.toBigBigDecimal(), INCH)
{
    object INCH : Unit<Length>(Length::class)
    {
        override val symbol = "in"
        override val scale = 0.0254.toBigBigDecimal()
    }
}