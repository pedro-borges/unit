package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class TimeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 / 2,
                                  unit = TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 0 + 1,
                                  unit = TIME1),
            actual = TimeAmount(amount = 0,
                                unit = TIME1) +
                     TimeAmount(amount = 1,
                                unit = TIME1))
    }

    @Test
    fun plusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 0 + 2,
                                  unit = TIME1),
            actual = TimeAmount(amount = 0,
                                unit = TIME1) +
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun minusSameTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 1 - 1,
                                  unit = TIME1),
            actual = TimeAmount(amount = 1,
                                unit = TIME1) -
                     TimeAmount(amount = 1,
                                unit = TIME1))
    }

    @Test
    fun minusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 - 2,
                                  unit = TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) -
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun divSpace()
    {
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 2 / 2,
                                  unit = TIME1 / LENGTH1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSamePower()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     PowerAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1 * LENGTH1) /
                                        (TIME1 * TIME1 * TIME1)))
    }

    @Test
    fun timesOtherPower()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     PowerAmount(amount = 2 * (2 * 2 * 2),
                                 unit = (MASS1 * LENGTH1 * LENGTH1) /
                                        (TIME2 * TIME2 * TIME2)))
    }

    @Test
    fun timesElectricCurrent()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 2 / 2,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                     (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, out Unit<ElectricCurrent>, out Unit<Mass>, out Unit<Length>>>(
            expected = ElectricCapacitanceAmount(amount = 8 / (2 * 2 * 2),
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = TimeAmount(amount = 8,
                                unit = TIME1) /
                     ElectricResistanceAmount(amount = 2 * (2 * 2),
                                              unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                     (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricCapacitance()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 2 / 2,
                                                unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                       (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun divOtherElectricCapacitance()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>, out Unit<ElectricCurrent>>>(
            expected = ElectricResistanceAmount(amount = 16 / (2 * 2 * 2 * 2),
                                                unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                       (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)),
            actual = TimeAmount(amount = 16,
                                unit = TIME1) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME2 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                      (MASS2 * LENGTH2 * LENGTH2)))
    }
}
