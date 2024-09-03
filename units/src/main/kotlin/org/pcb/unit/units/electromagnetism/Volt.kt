package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricTension
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

@Suppress("unused")
class Volt(amount: Number)
    : ElectricTensionAmount<KILOGRAM, METER, SECOND, AMPERE>(amount = amount,
                                                             major = KILOGRAM * METER * METER,
                                                             minor = SECOND * SECOND * SECOND * AMPERE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object VOLT : Unit<ElectricTension>(ElectricTension::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "V"
        override val scale = 1.toBigBigDecimal()
    }
}
