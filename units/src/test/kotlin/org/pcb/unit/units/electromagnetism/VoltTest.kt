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

class VoltTest
{
    @Test
    fun unit()
    {
        Volt(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) * (Meter(1) / Second(1)) / Second(1) / Ampere(1)
    }

    private infix fun Volt.equivalent(other: ElectricTensionAmount<*, *, *, *>) =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(this, Volt(0) + other)
}