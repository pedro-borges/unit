package org.pcb.units.amount.electromagnetism

import org.pcb.units.Unit
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.domain.ElectricTension
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Volt(amount: Number)
    : ElectricTensionAmount<KILOGRAM, METER, SECOND, AMPERE>(amount, KILOGRAM, METER, METER, SECOND, SECOND, SECOND, AMPERE)
{
    object VOLT : Unit<ElectricTension>(ElectricTension::class)
    {
        override val symbol = "V"
        override val scale = 1.toBigBigDecimal()
    }
}
