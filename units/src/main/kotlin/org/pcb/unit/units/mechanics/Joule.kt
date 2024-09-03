package org.pcb.unit.units.mechanics

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Energy
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

@Suppress("unused")
class Joule(amount: Number)
    : EnergyAmount<KILOGRAM, METER,  SECOND>(amount = amount,
                                             major = KILOGRAM * METER * METER,
                                             minor = SECOND * SECOND)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object JOULE : Unit<Energy>(Energy::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "J"
        override val scale = 1.toBigBigDecimal()
    }
}