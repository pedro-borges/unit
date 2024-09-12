package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Second.SECOND

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
