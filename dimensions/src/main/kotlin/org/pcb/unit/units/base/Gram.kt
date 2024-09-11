package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram.GRAMS

class Gram(amount: Number,
           magnitude: Magnitude = UNI)
    : MassAmount<GRAMS>(
    amount = amount.toBigBigDecimal(),
    unit = GRAMS(magnitude))
{
    open class GRAMS(magnitude: Magnitude = UNI) :Unit<Mass>(Mass::class, magnitude.symbol + "g", magnitude.scale * 0.001.toBigBigDecimal())

    object GRAM : GRAMS(UNI)
}
