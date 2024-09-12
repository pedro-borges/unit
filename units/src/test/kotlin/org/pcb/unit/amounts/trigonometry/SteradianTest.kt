package org.pcb.unit.amounts.trigonometry

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.DECI
import org.pcb.unit.Magnitude.HECTO
import org.pcb.unit.amounts.SolidAngleAmount
import org.pcb.unit.amounts.base.Radian
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.trigonometry.Steradian.STERADIAN

class SteradianTest
{
    private infix fun Steradian.equivalent(other: SolidAngleAmount<*>) =
        assertEquals(this, Steradian(0) + other)

    @Test
    fun scales()
    {
        Steradian(4) equivalent Radian(2) * Radian(2)
        Steradian(4) equivalent Radian(20, DECI) * Radian(20, DECI)
        Steradian(2) equivalent SolidAngleAmount(2, RADIAN)
        Steradian(2) equivalent SolidAngleAmount(2, STERADIAN)

        Steradian(1, HECTO) equivalent Steradian(100)
        Steradian(1, HECTO) equivalent Steradian(1, HECTO)
    }
}
