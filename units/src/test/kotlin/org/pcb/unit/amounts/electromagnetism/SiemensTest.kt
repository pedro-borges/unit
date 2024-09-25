package org.pcb.unit.amounts.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.ElectricConductanceAmount
import org.pcb.unit.amounts.electromagnetism.Siemens.SIEMENS
import org.pcb.unit.amounts.frequency.Hertz
import org.pcb.unit.base.Ampere
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.kilo

class SiemensTest
{
    private infix fun Siemens.equivalent(other: ElectricConductanceAmount<*, *, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
//        Siemens(0.25) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2) / Ampere(2) TODO
        Siemens(2) equivalent ElectricConductanceAmount(2, U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
        Siemens(2) equivalent ElectricConductanceAmount(2, SIEMENS)
    }

    @Test
    fun ampereDivVolt() =
        assertEquals(
            expected = Siemens(6 / 3),
            actual = Ampere(6) / Volt(3))

    @Test
    fun hertzTimesFarad() =
        assertEquals(
            expected = Siemens(2 * 3),
            actual = Hertz(2) * Farad(3))

    @Test
    fun faradTimesHertz() =
        assertEquals(
            expected = Siemens(2 * 3),
            actual = Farad(2) * Hertz(3))

    @Test
    fun oneDivOhm() =
        assertEquals(
            expected = Siemens(6 / 3),
            actual = Numeric(6) / Ohm(3))
}