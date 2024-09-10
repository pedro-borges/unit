package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Weber.WEBER
import org.pcb.unit.units.mechanics.Joule
import org.pcb.unit.units.space.SquareMeter

class WeberTest
{
    private infix fun Weber.equivalent(other: MagneticFluxAmount<*, *, *, *>) =
        assertEquals(this, Weber(0) + other)

    @Test
    fun unit()
    {
        Weber(1) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Ampere(2)
        Weber(2) equivalent MagneticFluxAmount(2, U2(KILOGRAM, METER) to (U2(SECOND, AMPERE)))
        Weber(2) equivalent MagneticFluxAmount(2, WEBER)
    }

    @Test
    fun coulombTimesOhm() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Coulomb(2) * Ohm(3))

    @Test
    fun jouleDivAmpere() =
        assertEquals(
            expected = Weber(6 / 3),
            actual = Joule(6) / Ampere(3))

    @Test
    fun ohmTimesCoulomb() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Ohm(2) * Coulomb(3))

    @Test
    fun secondTimesVolt() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Second(2) * Volt(3))

    @Test
    fun squareMeterTimesWeber() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = SquareMeter(2) * Tesla(3))

    @Test
    fun voltTimesSecond() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Volt(2) * Second(3))

    @Test
    fun weberTimesSquareMeter() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Tesla(2) * SquareMeter(3))
}
