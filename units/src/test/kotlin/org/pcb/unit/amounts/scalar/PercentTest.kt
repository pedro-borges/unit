package org.pcb.unit.amounts.scalar

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.units.ScalarAmount

class PercentTest
{
    private infix fun Percent.equivalent(other: ScalarAmount<*>) =
        assertEquals(this, Percent(0) + other)

    @Test
    fun unit()
    {
        Percent(200) equivalent Numeric(1)
        Percent(200) equivalent ScalarAmount(2, NUMERIC)
        assertEquals(Percent(2), Percent(1) + Percent(1))
    }
}