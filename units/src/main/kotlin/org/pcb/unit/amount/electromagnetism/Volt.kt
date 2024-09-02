package org.pcb.unit.amount.electromagnetism

import org.pcb.unit.Unit
import org.pcb.unit.amount.ElectricTensionAmount
import org.pcb.unit.amount.base.Ampere.AMPERE
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.ElectricTension
import org.pcb.unit.toBigBigDecimal

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
