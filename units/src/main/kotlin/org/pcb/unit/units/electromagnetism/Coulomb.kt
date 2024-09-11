package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere.AmpereUnit
import org.pcb.unit.units.base.Second.SECOND

class Coulomb(amount: Number,
              magnitude: Magnitude = UNI)
    : ElectricChargeAmount<Unit<ElectricCurrent>, Unit<Time>>(
    amount = amount,
    unit = U2(AmpereUnit(magnitude), SECOND))
{
    open class CoulombUnit(magnitude: Magnitude)
        : Unit<ElectricCharge>(ElectricCharge::class, "C", magnitude, 1.toBigBigDecimal())

    object COULOMB : CoulombUnit(UNI)
}

