package org.pcb.unit.units.electromagnetism

import org.pcb.unit.U2
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
    unit = U2(AMPERE, SECOND))
{
    object COULOMB : Unit<ElectricCharge>(ElectricCharge::class, "C", 1.toBigBigDecimal())
}