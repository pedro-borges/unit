package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.electromagnetism.Farad.FARAD
import org.pcb.unit.units.frequency.Hertz

class FaradTest
{
    private infix fun Farad.equivalent(other: ElectricCapacitanceAmount<*, *, *, *>) =
        assertEquals(this, Farad(0) + other)

    @Test
    fun unit()
    {
//        Farad(2) equivalent Kilogram(2) TODO finish
        Farad(2) equivalent ElectricCapacitanceAmount(2, U2(SECOND, AMPERE) to U2(KILOGRAM, METER))
        Farad(2) equivalent ElectricCapacitanceAmount(2, FARAD)
    }

    @Test
    fun coulombDivVolt() =
        assertEquals(
            expected = Farad(6 / 3),
            actual = Coulomb(6) / Volt(3))

    @Test
    fun secondDivOhm() =
        assertEquals(
            expected = Farad(6 / 3),
            actual = Second(6) / Ohm(3))

    @Test
    fun secondTimesSiemens() =
        assertEquals(
            expected = Farad(2 * 3),
            actual = Second(2) * Siemens(3))

    @Test
    fun siemensDivHertz() =
        assertEquals(
            expected = Farad(6 / 3),
            actual = Siemens(6) / Hertz(3))

    @Test
    fun siemensTimesSecond() =
        assertEquals(
            expected = Farad(2 * 3),
            actual = Siemens(2) * Second(3))
}