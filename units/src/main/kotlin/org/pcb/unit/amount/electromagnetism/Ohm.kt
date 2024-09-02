package org.pcb.unit.amount.electromagnetism

import org.pcb.unit.Unit
import org.pcb.unit.amount.ElectricResistanceAmount
import org.pcb.unit.amount.base.Ampere.AMPERE
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Ohm(amount: Number)
    : ElectricResistanceAmount<KILOGRAM, METER, SECOND, AMPERE>(amount, KILOGRAM, METER, METER, SECOND, SECOND, SECOND, AMPERE, AMPERE)
{
    object OHM : Unit<ElectricResistance>(ElectricResistance::class)
    {
        override val symbol = "Ω"
        override val scale = 1.toBigBigDecimal()
    }
}
