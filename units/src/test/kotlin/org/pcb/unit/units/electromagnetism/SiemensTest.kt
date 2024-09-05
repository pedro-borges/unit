package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.div
import org.pcb.unit.units.ElectricConductivityAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.electromagnetism.Siemens.SIEMENS

class SiemensTest
{
    private infix fun Siemens.equivalent(other: ElectricConductivityAmount<*, *, *, *>) =
        assertEquals(this, Siemens(0) + other)

    @Test
    fun unit()
    {
//        Siemens(0.25) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2) / Ampere(2) TODO
        Siemens(2) equivalent ElectricConductivityAmount(2, SIEMENS)
    }

    @Test
    fun ampereDivVolt() =
        assertEquals(
            expected = Siemens(6 / 3),
            actual = Ampere(6) / Volt(3))

    @Test
    fun oneDivOhm() =
        assertEquals(
            expected = Siemens(6 / 3),
            actual = 6 / Ohm(3))
}