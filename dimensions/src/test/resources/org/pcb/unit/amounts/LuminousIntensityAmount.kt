package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.toBigDecimal

open class LuminousIntensityAmount<LI : Unit<LuminousIntensity>>(
        amount: Number,
        unit: LI)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit to 1)))
{
    val luminousIntensity = unit

    $operators
}
