package org.pcb.unit.amounts.frequency

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.FrequencyAmount
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Siemens
import org.pcb.unit.amounts.frequency.Hertz.HERTZ

class HertzTest
{
    private infix fun Hertz.equivalent(other: FrequencyAmount<*>) =
        assertEquals(this, Hertz(0) + other)

    @Test
    fun unit()
    {
        Hertz(2) equivalent Numeric(2) / Second(1)
        Hertz(2) equivalent FrequencyAmount(2, NUMERIC to SECOND)
        Hertz(2) equivalent FrequencyAmount(2, HERTZ)
    }

    @Test
    fun scalarDivTime() =
        assertEquals(
            expected = Hertz(6 / 3),
            actual = Numeric(6) / Second(3))

    @Test
    fun siemensDivFarad() =
        assertEquals(
            expected = Hertz(6 / 3),
            actual = Siemens(6) / org.pcb.unit.amounts.electromagnetism.Farad(3))
}