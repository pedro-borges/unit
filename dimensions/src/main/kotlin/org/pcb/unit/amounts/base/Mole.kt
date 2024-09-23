package org.pcb.unit.amounts.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AmountOfSubstanceAmount
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.toBigDecimal

class Mole(amount: Number,
           magnitude: Magnitude = UNI)
    : AmountOfSubstanceAmount<Unit<AmountOfSubstance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = MOLE)
{
    open class MeterUnit(magnitude: Magnitude)
        : Unit<AmountOfSubstance>(AmountOfSubstance::class, "mol", 1.toBigDecimal(), magnitude)

    object MOLE : MeterUnit(UNI)
}
