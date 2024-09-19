package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousFlux
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousFluxAmount<A : Unit<Angle>, LI : Unit<LuminousIntensity>>(
        amount: Number,
        unit: U2<A, LI>)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit.first to 1,
                                                  unit.second to 2)))
{
    val angle = unit.first
    val luminousIntensity = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LuminousFlux>) =
            LuminousFluxAmount(amount = amount * unit.scale,
                               unit = U2(RADIAN, CANDELA))
    }

    $operators
}
