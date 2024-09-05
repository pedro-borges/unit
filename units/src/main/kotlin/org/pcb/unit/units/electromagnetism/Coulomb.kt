package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Second.SECOND

class Coulomb(amount: Number)
    : ElectricChargeAmount<Unit<ElectricCurrent>, Unit<Time>>(
    amount = amount,
    unit = AMPERE * SECOND)
{
    object COULOMB : Unit<ElectricCharge>(ElectricCharge::class)
    {
        override val symbol = "C"
        override val scale = 1.toBigBigDecimal()
    }
}