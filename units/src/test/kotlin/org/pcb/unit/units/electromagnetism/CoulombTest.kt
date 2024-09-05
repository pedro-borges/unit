package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Coulomb.COULOMB
import org.pcb.unit.units.mechanics.Joule

class CoulombTest
{
    private infix fun Coulomb.equivalent(other: ElectricChargeAmount<*, *>) =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(this, Coulomb(0) + other)

    @Test
    fun unit()
    {
        Coulomb(2 * 2) equivalent Ampere(2) * Second(2)
        Coulomb(2) equivalent ElectricChargeAmount(2, COULOMB)
    }

    @Test
    fun ampereTimesSecond() =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(
            expected = Coulomb(2 * 3),
            actual = Ampere(2) * Second(3))

    @Test
    fun faradTimesVolt() =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(
            expected = Coulomb(2 * 3),
            actual = Farad(2) * Volt(3))

    @Test
    fun jouleDivVolt() =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(
            expected = Coulomb(6 / 3),
            actual = Joule(6) / Volt(3))

    @Test
    fun secondTimesAmpere() =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(
            expected = Coulomb(2 * 3),
            actual = Second(2) * Ampere(3))

    @Test
    fun voltTimesFarad() =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(
            expected = Coulomb(2 * 3),
            actual = Volt(2) * Farad(3))

    @Test
    fun weberDivOhm() =
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, SECOND>>(
            expected = Coulomb(6 / 3),
            actual = Weber(6) / Ohm(3))
}
