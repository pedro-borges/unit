package org.pcb.unit.units.mass

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Drachm(amount: Number)
    : MassAmount<Unit<Mass>>(
    amount = amount,
    unit = DRACHM)
{
    object DRACHM : Unit<Mass>(Mass::class)
    {
        override val symbol = "dr"
        override val scale = 0.0017718451953125.toBigBigDecimal()
    }
}