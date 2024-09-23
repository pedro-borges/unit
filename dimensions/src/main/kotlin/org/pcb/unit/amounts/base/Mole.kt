package org.pcb.unit.amounts.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.SubstanceAmount
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.toBigDecimal

class Mole(amount: Number,
           magnitude: Magnitude = UNI)
    : SubstanceAmount<Unit<Substance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = MOLE)
{
    open class MeterUnit(magnitude: Magnitude)
        : Unit<Substance>(Substance::class, "mol", 1.toBigDecimal(), magnitude)

    object MOLE : MeterUnit(UNI)
}
