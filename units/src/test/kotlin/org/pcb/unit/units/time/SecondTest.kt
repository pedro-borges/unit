package org.pcb.unit.units.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Farad
import org.pcb.unit.units.electromagnetism.Ohm
import org.pcb.unit.units.electromagnetism.Siemens
import org.pcb.unit.units.electromagnetism.Volt
import org.pcb.unit.units.electromagnetism.Weber
import org.pcb.unit.units.frequency.Hertz
import org.pcb.unit.units.mechanics.Joule
import org.pcb.unit.units.mechanics.Watt
import org.pcb.unit.units.scalar.Numeric

class SecondTest
{
    private infix fun Second.equivalent(other: TimeAmount<*>) =
        assertEquals(this, Second(0) + other)

    @Test
    fun scales()
    {
        Second(1) equivalent Second(1)
        Second(60) equivalent Minute(1)
        Second(60 * 60) equivalent Hour(1)
        Second(60 * 60 * 24) equivalent Day(1)

        Second(1) equivalent TimeAmount(1, SECOND)
    }

    @Test
    fun scalarDivHertz() =
        assertEquals(
            expected = Second(2 * 3),
            actual = Numeric(2) / Hertz(3))

    @Test
    fun jouleDivWatt() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Joule(6) / Watt(3))

    @Test
    fun ohmTimesFarad() =
        assertEquals(
            expected = Second(2 * 3),
            actual = Ohm(2) * Farad(3))

    @Test
    fun faradTimesOhm() =
        assertEquals(
            expected = Second(2 * 3),
            actual = Farad(2) * Ohm(3))

    @Test
    fun weberDivVolt() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Weber(6) / Volt(3))

    @Test
    fun faradDivSiemens() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Farad(6) / Siemens(3))
}
