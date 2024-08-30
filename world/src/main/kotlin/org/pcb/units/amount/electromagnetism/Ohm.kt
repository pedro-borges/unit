package org.pcb.units.amount.electromagnetism

import org.pcb.units.Unit
import org.pcb.units.amount.ElectricResistanceAmount
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.domain.ElectricResistance
import org.pcb.units.toBigBigDecimal

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
