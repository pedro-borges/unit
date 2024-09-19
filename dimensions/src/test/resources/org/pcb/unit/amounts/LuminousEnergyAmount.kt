package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousEnergy
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Time
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousEnergyAmount<A : Unit<Angle>, LI : Unit<LuminousIntensity>, T : Unit<Time>>(
        amount: Number,
        unit: U3<A, LI, T>)
    : Amount<LuminousEnergy>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = LuminousEnergy::class,
                                 units = mapOf(unit.first to 2,
                                               unit.second to 1,
                                               unit.third to 1)))
{
    val angle = unit.first
    val luminousIntensity = unit.second
    val time = unit.third

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LuminousEnergy>) =
            LuminousEnergyAmount(amount = amount * unit.scale,
                                 unit = U3(RADIAN, CANDELA, SECOND))
    }

    $operators
}
