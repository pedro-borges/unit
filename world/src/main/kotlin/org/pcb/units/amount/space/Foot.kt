package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Foot(amount: Number)
    : SpaceAmount<FOOT>(amount.toBigBigDecimal(), FOOT)
{
    object FOOT : Unit<Space>(Space::class)
    {
        override val symbol = "ft"
        override val scale = 0.3048.toBigBigDecimal()
    }
}