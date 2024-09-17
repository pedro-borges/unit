package org.pcb.unit.amounts.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.U2
import org.pcb.unit.amounts.MagneticFluxAmount
import org.pcb.unit.amounts.base.Ampere
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Weber.WEBER
import org.pcb.unit.amounts.mechanics.Joule
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.kilo

class WeberTest
{
    private infix fun Weber.equivalent(other: MagneticFluxAmount<*, *, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Weber(1) equivalent Gram(2, KILO) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Ampere(2)
        Weber(2) equivalent MagneticFluxAmount(2, U2(METER, kilo(GRAM)) to (U2(AMPERE, SECOND)))
        Weber(2) equivalent MagneticFluxAmount(2, WEBER)
    }

    @Test
    fun ampereTimesHenry() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Ampere(2) * Henry(3))

    @Test
    fun coulombTimesOhm() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Coulomb(2) * Ohm(3))

    @Test
    fun henryTimesAmpere() =
        assertEquals(
            expected = Weber(2 * 3),
            actual = Henry(2) * Ampere(3))

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
