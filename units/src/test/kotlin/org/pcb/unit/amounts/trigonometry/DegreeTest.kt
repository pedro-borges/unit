package org.pcb.unit.amounts.trigonometry

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Constants
import org.pcb.unit.amounts.trigonometry.Degree.DEGREE
import org.pcb.unit.amounts.trigonometry.Radian.RADIAN
import org.pcb.unit.units.AngleAmount

class DegreeTest
{
    private infix fun Degree.equivalent(other: AngleAmount<*>) =
        assertEquals(this, Degree(0) + other)

    @Test
    fun scales()
    {
        Degree(180) equivalent AngleAmount(Constants.PI, RADIAN)

        Degree(1) equivalent AngleAmount(1, DEGREE)
    }
}
