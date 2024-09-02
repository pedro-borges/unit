package org.pcb.units.amount.electromagnetism

import org.pcb.units.Unit
import org.pcb.units.amount.ElectricChargeAmount
import org.pcb.units.amount.base.Ampere.AMPERE
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.dimension.ElectricCharge
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Coulomb(amount: Number)
    : ElectricChargeAmount<AMPERE, SECOND>(amount, AMPERE, SECOND)
{
    object COULOMB : Unit<ElectricCharge>(ElectricCharge::class)
    {
        override val symbol = "C"
        override val scale = 1.toBigBigDecimal()
    }
}
