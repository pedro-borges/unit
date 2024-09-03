package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Second

class VoltTest
{
    @Test
    fun unit()
    {
        Volt(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) * (Meter(1) / Second(1)) / Second(1) / Ampere(1)
    }

    private infix fun Volt.equivalent(other: ElectricTensionAmount<*, *, *, *>) =
        assertEquals(this, Volt(0) + other)
}