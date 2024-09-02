package org.pcb.unit.amount.electromagnetism

import org.pcb.unit.Unit
import org.pcb.unit.amount.ElectricChargeAmount
import org.pcb.unit.amount.base.Ampere.AMPERE
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.toBigBigDecimal

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
