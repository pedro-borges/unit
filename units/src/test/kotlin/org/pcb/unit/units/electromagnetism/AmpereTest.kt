package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.mechanics.Watt

class AmpereTest
{
    private infix fun Ampere.equivalent(other: ElectricCurrentAmount<*>) =
        assertEquals(this, Ampere(0) + other)

    @Test
    fun unit()
    {
        Ampere(2) equivalent ElectricCurrentAmount(2, AMPERE)
    }

    @Test
    fun coulombDivSecond() =
        assertEquals(
            expected = Ampere(6 / 3),
            actual = Coulomb(6) / Second(3))

    @Test
    fun siemensTimesVolt() =
        assertEquals(
            expected = Ampere(2 * 3),
            actual = Siemens(2) * Volt(3))

    @Test
    fun voltDivOhm() =
        assertEquals(
            expected = Ampere(6 / 3),
            actual = Volt(6) / Ohm(3))

    @Test
    fun voltTimesSiemens() =
        assertEquals(
            expected = Ampere(2 * 3),
            actual = Volt(2) * Siemens(3))

    @Test
    fun wattDivVolt() =
        assertEquals(
            expected = Ampere(6 / 3),
            actual = Watt(6) / Volt(3))
}