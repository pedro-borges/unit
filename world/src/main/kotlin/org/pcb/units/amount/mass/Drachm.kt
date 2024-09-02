package org.pcb.units.amount.mass

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.mass.Drachm.DRACHM
import org.pcb.units.dimension.Mass
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Drachm(amount: Number)
    : MassAmount<DRACHM>(amount.toBigBigDecimal(), DRACHM)
{
    object DRACHM : Unit<Mass>(Mass::class)
    {
        override val symbol = "dr"
        override val scale = 1.7718451953125.toBigBigDecimal()
    }
}