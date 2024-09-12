package org.pcb.unit.units.trigonometry

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Constants
import org.pcb.unit.units.AngleAmount
import org.pcb.unit.units.trigonometry.Degree.DEGREE
import org.pcb.unit.units.trigonometry.Radian.RADIAN

class RadianTest
{
    private infix fun Radian.equivalent(other: AngleAmount<*>) =
        assertEquals(this, Radian(0) + other)

    @Test
    fun scales()
    {
        Radian(Constants.PI) equivalent AngleAmount(180, DEGREE)

        Radian(1) equivalent AngleAmount(1, RADIAN)
    }
}
