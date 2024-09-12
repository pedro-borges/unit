package org.pcb.unit.units.scalar

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.base.Numeric.NUMERIC
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Ohm
import org.pcb.unit.units.electromagnetism.Siemens
import org.pcb.unit.units.frequency.Hertz
import org.pcb.unit.units.mechanics.MeterPerSecond
import org.pcb.unit.units.mechanics.SecondPerMeter

class NumericTest
{
    private infix fun Numeric.equivalent(other: ScalarAmount<*>) =
        assertEquals(this, Numeric(0) + other)

    @Test
    fun unit()
    {
        Numeric(2) equivalent ScalarAmount(2, NUMERIC)
        assertEquals(Numeric(2), Numeric(1) + Numeric(1))
    }

    @Test
    fun hertzTimesSecond()
    {
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Hertz(2) * Second(3))
    }

    @Test
    fun meterPerSecondTimesSecondPerMeter()
    {
        assertEquals(
            expected = Numeric(2 * 3),
            actual = MeterPerSecond(2) * SecondPerMeter(3))
    }

    @Test
    fun ohmTimesSiemens() =
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Ohm(2) * Siemens(3))

    @Test
    fun secondTimesHertz()
    {
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Second(2) * Hertz(3))
    }

    @Test
    fun secondPerMeterTimesMeterPerSecond() =
        assertEquals(
            expected = Numeric(2 * 3),
            actual = SecondPerMeter(2) * MeterPerSecond(3))

    @Test
    fun siemensTimesOhm()
    {
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Siemens(2) * Ohm(3))
    }
}