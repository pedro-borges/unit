package org.pcb.unit.units.mass

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.mass.Drachm.DRACHM

@Suppress("unused")
class Drachm(amount: Number)
    : MassAmount<DRACHM>(amount.toBigBigDecimal(), DRACHM)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object DRACHM : Unit<Mass>(Mass::class)
    {
        override val symbol = "dr"
        override val scale = 0.0017718451953125.toBigBigDecimal()
    }
}