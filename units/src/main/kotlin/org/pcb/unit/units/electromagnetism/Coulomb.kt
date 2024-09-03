package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Second.SECOND

@Suppress("unused")
class Coulomb(amount: Number)
    : ElectricChargeAmount<AMPERE, SECOND>(amount = amount,
                                           major = AMPERE * SECOND)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object COULOMB : Unit<ElectricCharge>(ElectricCharge::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "C"
        override val scale = 1.toBigBigDecimal()
    }
}
