package org.pcb.units.amount.electromagnetism

import org.pcb.units.Unit
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.base.Ampere.AMPERE
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.dimension.ElectricTension
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
