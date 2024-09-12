package org.pcb.unit.amounts.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.ElectricChargeAmount
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.ElectricCharge
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

class Coulomb(amount: Number,
              magnitude: Magnitude = UNI)
    : ElectricChargeAmount<Unit<ElectricCurrent>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U2(AMPERE, SECOND))
{
    open class CoulombUnit(magnitude: Magnitude)
        : Unit<ElectricCharge>(ElectricCharge::class, "C", 1.toBigDecimal(), magnitude)

    object COULOMB : CoulombUnit(UNI)
}
