package org.pcb.unit.amounts.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.U2
import org.pcb.unit.amounts.PowerAmount
import org.pcb.unit.amounts.base.Ampere
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Volt
import org.pcb.unit.amounts.mechanics.Watt.WATT
import org.pcb.unit.kilo

class WattTest
{
    private infix fun Watt.equivalent(other: PowerAmount<*, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Watt(1) equivalent Gram(2, KILO) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2)
        Watt(2) equivalent PowerAmount(2, U2(kilo(GRAM), METER) to SECOND)
        Watt(2) equivalent PowerAmount(2, WATT)
    }

    @Test
    fun ampereTimesVolt() =
        assertEquals(
            expected = Watt(2 * 3),
            actual = Ampere(2) * Volt(3))

    @Test
    fun jouleDivSecond() =
        assertEquals(
            expected = Watt(6 / 3),
            actual = Joule(6) / Second(3))

    @Test
    fun meterPerSecondTimesNewton() =
        assertEquals(
            expected = Watt(2 * 3),
            actual = MeterPerSecond(2) * Newton(3))

    @Test
    fun newtonTimesMeterPerSecond() =
        assertEquals(
            expected = Watt(2 * 3),
            actual = Newton(2) * MeterPerSecond(3))

    @Test
    fun voltTimesAmpere() =
        assertEquals(
            expected = Watt(2 * 3),
            actual = Volt(2) * Ampere(3))
}