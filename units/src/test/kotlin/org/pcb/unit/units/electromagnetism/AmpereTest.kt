package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.mechanics.Watt

class AmpereTest
{
    @Test
    fun unit()
    {
        Ampere(2) equivalent ElectricCurrentAmount(2, AMPERE)

        assertEquals(
            expected = Ampere(6 / 3),
            actual = Watt(6) / Volt(3))

        assertEquals(
            expected = Ampere(6 / 3),
            actual = Volt(6) / Ohm(3))

        assertEquals(
            expected = Ampere(2 * 3),
            actual = Siemens(2) * Volt(3))

        assertEquals(
            expected = Ampere(2 * 3),
            actual = Volt(2) * Siemens(3))
    }

    private infix fun Ampere.equivalent(other: ElectricCurrentAmount<*>) =
        assertEquals(this, Ampere(0) + other)
}