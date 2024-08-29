package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Inch(amount: Number)
    : SpaceAmount<INCH>(amount.toBigBigDecimal(), INCH)
{
    object INCH : Unit<Space>(Space::class)
    {
        override val symbol = "in"
        override val cannon = 0.0254.toBigBigDecimal()
    }
}