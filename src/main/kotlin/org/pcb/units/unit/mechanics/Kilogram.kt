package org.pcb.units.unit.mechanics

import java.math.BigDecimal.ONE
import org.pcb.units.domain.Mass
import org.pcb.units.unit.Unit

class Kilogram : Unit<Mass>(Mass::class)
{
    override val symbol = "Kg"
    override val cannon = ONE

    companion object
    {
        val KILOGRAMS = Kilogram()
    }
}