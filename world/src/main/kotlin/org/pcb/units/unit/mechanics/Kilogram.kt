package org.pcb.units.unit.mechanics

import org.pcb.units.Unit
import org.pcb.units.domain.Mass
import org.pcb.units.toBigBigDecimal

object Kilogram : Unit<Mass>(Mass::class)
{
    override val symbol = "Kg"
    override val cannon = 1.toBigBigDecimal()
}