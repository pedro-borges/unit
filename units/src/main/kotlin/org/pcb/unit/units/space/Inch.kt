package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Inch(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = INCH)
{
    object INCH : Unit<Length>(Length::class)
    {
        override val symbol = "in"
        override val scale = 0.0254.toBigBigDecimal()
    }
}