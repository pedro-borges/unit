package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Luminance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminanceAmount<D : Unit<Distance>, LI : Unit<LuminousIntensity>>(
        amount: Number,
        unit: Pair<LI, D>)
    : Amount<Luminance>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Luminance::class,
                            units = mapOf(unit.major to 1,
                                          unit.minor to -2)))
{
    val luminousIntensity = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Luminance>) =
            LuminanceAmount(amount = amount * unit.scale,
                            unit = CANDELA to METER)
    }

    $operators
}
