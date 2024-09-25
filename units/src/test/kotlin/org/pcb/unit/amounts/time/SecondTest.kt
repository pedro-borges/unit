package org.pcb.unit.amounts.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.amounts.electromagnetism.Farad
import org.pcb.unit.amounts.electromagnetism.Ohm
import org.pcb.unit.amounts.electromagnetism.Siemens
import org.pcb.unit.amounts.electromagnetism.Volt
import org.pcb.unit.amounts.electromagnetism.Weber
import org.pcb.unit.amounts.frequency.Hertz
import org.pcb.unit.amounts.mechanics.Joule
import org.pcb.unit.amounts.mechanics.Watt
import org.pcb.unit.amounts.optics.Lumen
import org.pcb.unit.amounts.optics.Talbot
import org.pcb.unit.base.Numeric
import org.pcb.unit.base.Second
import org.pcb.unit.base.Second.SECOND

class SecondTest
{
    private infix fun Second.equivalent(other: TimeAmount<*>) =
        assertEquals(this, other)

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
    fun faradDivSiemens() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Farad(6) / Siemens(3))

    @Test
    fun faradTimesOhm() =
        assertEquals(
            expected = Second(2 * 3),
            actual = Farad(2) * Ohm(3))

    @Test
    fun jouleDivWatt() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Joule(6) / Watt(3))

    @Test
    fun numericDivHertz() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Numeric(6) / Hertz(3))

    @Test
    fun ohmTimesFarad() =
        assertEquals(
            expected = Second(2 * 3),
            actual = Ohm(2) * Farad(3))

    @Test
    fun talbotDivLumen() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Talbot(6) / Lumen(3))

    @Test
    fun weberDivVolt() =
        assertEquals(
            expected = Second(6 / 3),
            actual = Weber(6) / Volt(3))
}
