package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Volt.VOLT

class VoltTest
{
    @Test
    fun unit()
    {
        Volt(0.5) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2)
        Volt(2) equivalent ElectricTensionAmount(2, VOLT)
    }

    private infix fun Volt.equivalent(other: ElectricTensionAmount<*, *, *, *>) =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(this, Volt(0) + other)
}