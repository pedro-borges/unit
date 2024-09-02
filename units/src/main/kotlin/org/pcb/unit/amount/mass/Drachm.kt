package org.pcb.unit.amount.mass

import org.pcb.unit.Unit
import org.pcb.unit.amount.MassAmount
import org.pcb.unit.amount.mass.Drachm.DRACHM
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal

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