package org.pcb.unit.amounts.optics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.LuminousEnergyAmount
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousEnergy
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

class Talbot(amount: Number,
             magnitude: Magnitude = UNI)
    : LuminousEnergyAmount<Unit<LuminousIntensity>, Unit<Angle>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = U3(CANDELA, RADIAN, SECOND))
{
    open class TalbotUnit(magnitude: Magnitude)
        : Unit<LuminousEnergy>(LuminousEnergy::class, "T", 1.toBigDecimal(), magnitude)

    object TALBOT : TalbotUnit(UNI)
}