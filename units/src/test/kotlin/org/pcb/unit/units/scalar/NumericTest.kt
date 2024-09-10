package org.pcb.unit.units.scalar

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.electromagnetism.Ohm
import org.pcb.unit.units.electromagnetism.Siemens
import org.pcb.unit.units.mechanics.MeterPerSecond
import org.pcb.unit.units.mechanics.Newton
import org.pcb.unit.units.mechanics.Watt
import org.pcb.unit.units.scalar.Numeric.NUMERIC

class NumericTest
{
    private infix fun Numeric.equivalent(other: ScalarAmount<*>) =
        assertEquals(this, Numeric(0) + other)

    @Test
    fun unit()
    {
        Numeric(2) equivalent ScalarAmount(2, NUMERIC)
    }

    @Test
    fun ohmTimesSiemens() =
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Ohm(2) * Siemens(3))

    @Test
    fun siemensTimesOhm()
    {
        val expected = Numeric(2 * 3)
        val v1 = Siemens(2)
        val v2 = Ohm(3)
        val actual = v1 * v2
        assertEquals(
            expected = expected,
            actual = actual)
    }

    @Test
    fun newtonTimesMeterPerSecond()
    {
        val expected = Watt(2 * 3)
        val v1 = Newton(2)
        val v2 = MeterPerSecond(3)
        val actual = v1 * v2
        assertEquals(
            expected = expected,
            actual = actual)
    }

}