package org.pcb.units.amount.length

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.domain.Length
import org.pcb.units.toBigBigDecimal

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