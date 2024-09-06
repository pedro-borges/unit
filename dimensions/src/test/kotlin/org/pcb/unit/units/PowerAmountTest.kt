package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class PowerAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSamePower()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 0,
                                 unit = T2(MASS1, LENGTH1) / TIME1) +
                     PowerAmount(amount = 1,
                                 unit = T2(MASS1, LENGTH1) / TIME1))
    }

    @Test
    fun plusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 0,
                                 unit = T2(MASS1, LENGTH1) / TIME1) +
                     PowerAmount(amount = 1,
                                 unit = T2(MASS2, LENGTH2) / TIME2))
    }

    @Test
    fun minusSamePower()
    {
        assertEquals(
            expected = PowerAmount(amount = 0,
                                   unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 1,
                                 unit = T2(MASS1, LENGTH1) / TIME1) -
                     PowerAmount(amount = 1,
                                 unit = T2(MASS1, LENGTH1) / TIME1))
    }

    @Test
    fun minusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(amount = 0,
                                   unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 1,
                                 unit = T2(MASS1, LENGTH1) / TIME1) -
                     PowerAmount(amount = 1,
                                 unit = T2(MASS2, LENGTH2) / TIME2))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) /
                     PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) /
                     PowerAmount(amount = 2,
                                 unit = T2(MASS2, LENGTH2) / TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = T2(MASS1, LENGTH1) / TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = T2(MASS1, LENGTH1) / TIME2),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = T2(MASS1, LENGTH1) / T2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = T2(MASS2, LENGTH2) / T2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun divElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = T2(MASS1, LENGTH1) / T2(TIME1, ELECTRICCURRENT1)),
            actual = PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) / TIME1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }
}