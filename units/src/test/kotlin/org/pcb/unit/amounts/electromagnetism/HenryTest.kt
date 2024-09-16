package org.pcb.unit.amounts.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.InductanceAmount
import org.pcb.unit.amounts.base.Ampere
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.electromagnetism.Henry.HENRY
import org.pcb.unit.kilo

class HenryTest
{
    private infix fun Henry.equivalent(other: InductanceAmount<*, *, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Henry(2) equivalent InductanceAmount(2, U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
        Henry(2) equivalent InductanceAmount(2, HENRY)
    }

    @Test
    fun weberDivAmpere() =
        assertEquals(
            expected = Henry(6 / 3),
            actual = Weber(6) / Ampere(3))
}