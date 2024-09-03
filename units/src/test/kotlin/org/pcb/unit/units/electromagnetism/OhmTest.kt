package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Second

class OhmTest
{
    @Test
    fun unit()
    {
        Ohm(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) * (Meter(1) / Second(1)) / Second(1) / Ampere(1) / Ampere(1)
    }

    private infix fun Ohm.equivalent(other: ElectricResistanceAmount<*, *, *, *>) =
        assertEquals(this, Ohm(0) + other)
}