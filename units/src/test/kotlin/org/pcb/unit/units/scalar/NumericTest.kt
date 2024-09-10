package org.pcb.unit.units.scalar

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.electromagnetism.Ohm
import org.pcb.unit.units.electromagnetism.Siemens
import org.pcb.unit.units.scalar.Numeric.NUMERIC

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
    fun ohmTimesSiemens() =
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Ohm(2) * Siemens(3))

    @Test
    fun siemensTimesOhm()
    {
        assertEquals(
            expected = Numeric(2 * 3),
            actual = Siemens(2) * Ohm(3))
    }
}