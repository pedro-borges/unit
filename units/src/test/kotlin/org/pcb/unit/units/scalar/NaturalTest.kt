package org.pcb.unit.units.scalar

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.plus
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.electromagnetism.Ohm
import org.pcb.unit.units.electromagnetism.Siemens

class NaturalTest
{
    private infix fun Number.equivalent(other: ScalarAmount<*>) =
        assertEquals(this, 0 + other)

    @Test
    fun unit()
    {
        2 equivalent Natural(2)
        2 equivalent Percent(2 * 100)
    }

    @Test
    fun ohmTimesSiemens() =
        assertEquals(
            expected = BigDecimal(2),
            actual = Ohm(4) * Siemens(2))

    @Test
    fun siemensTimesOhm() =
        assertEquals(
            expected = BigDecimal(0.5),
            actual = Siemens(4) * Ohm(2))
}