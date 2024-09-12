package org.pcb.unit.amounts.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.U2
import org.pcb.unit.amounts.EnergyAmount
import org.pcb.unit.amounts.base.Ampere
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Volt
import org.pcb.unit.amounts.electromagnetism.Weber
import org.pcb.unit.amounts.mechanics.Joule.JOULE
import org.pcb.unit.amounts.space.Stere
import org.pcb.unit.amounts.thermodynamics.Pascal
import org.pcb.unit.kilo

class JouleTest
{
    private infix fun Joule.equivalent(other: EnergyAmount<*, *, *>) =
        assertEquals(this, Joule(0) + other)

    @Test
    fun unit()
    {
        Joule(2) equivalent Gram(2, KILO) * (Meter(2) / Second(2)) * (Meter(2) / Second(2))
        Joule(2) equivalent EnergyAmount(2, U2(kilo(GRAM), METER) to SECOND)
        Joule(2) equivalent EnergyAmount(2, JOULE)
    }

    @Test
    fun ampereTimesWeber() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Ampere(2) * Weber(3))

    @Test
    fun coulombTimesVolt() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = org.pcb.unit.amounts.electromagnetism.Coulomb(2) * Volt(3))

    @Test
    fun meterTimesNewton() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Meter(2) * Newton(3))

    @Test
    fun newtonTimesMeter() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Newton(2) * Meter(3))

    @Test
    fun pascalTimesStere() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Pascal(2) * Stere(3))

    @Test
    fun secondTimesWatt() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Second(2) * Watt(3))

    @Test
    fun stereTimesPascal() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Stere(2) * Pascal(3))

    @Test
    fun voltTimesCoulomb() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Volt(2) * org.pcb.unit.amounts.electromagnetism.Coulomb(3))

    @Test
    fun wattTimesSecond() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Watt(2) * Second(3))

    @Test
    fun weberTimesAmpere() =
        assertEquals(
            expected = Joule(2 * 3),
            actual = Weber(2) * Ampere(3))
}