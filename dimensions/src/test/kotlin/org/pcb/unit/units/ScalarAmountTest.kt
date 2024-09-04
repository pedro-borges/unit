package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.minus
import org.pcb.unit.plus
import org.pcb.unit.times
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1

class ScalarAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = BigDecimal(2 * 2),
            actual = 2 * Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = 2 / Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = BigDecimal(0 + 1),
            actual = 0 + BigDecimal(1))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = BigDecimal(0 + 1),
            actual = 0 + Percent(amount = 100))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = BigDecimal(1 - 1),
            actual = 1 - BigDecimal(1))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = BigDecimal(1 - 1),
            actual = 1 - Percent(amount = 100))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       unit = 1 / TIME1),
            actual = Percent(amount = 2 * 100) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divFrequency()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = Percent(amount = 2 * 100) /
                     FrequencyAmount(amount = 2,
                                     unit = 1 / TIME1))
    }

    @Test
    fun divElectricConductivity()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 2 * 2,
                                                unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                       (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)),
            actual = Percent(amount = 2 * 100) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)
                                                       / (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun divElectricResistance()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 2 / 2,
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)
                                                         / (MASS1 * LENGTH1 * LENGTH1)),
            actual = Percent(amount = 2 * 100) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                     (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)))
    }
}