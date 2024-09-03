package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

@Suppress("unused")
class Ohm(amount: Number)
    : ElectricResistanceAmount<KILOGRAM, METER, SECOND, AMPERE>(amount, KILOGRAM, METER, METER, SECOND, SECOND, SECOND, AMPERE, AMPERE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object OHM : Unit<ElectricResistance>(ElectricResistance::class)
    {
        override val symbol = "Ω"
        override val scale = 1.toBigBigDecimal()
    }
}
