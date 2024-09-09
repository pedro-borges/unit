package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Numeric.NUMERIC
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
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSamePower()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 0,
                                 unit = U2(MASS1, LENGTH1) to TIME1) +
                     PowerAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun plusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 0,
                                 unit = U2(MASS1, LENGTH1) to TIME1) +
                     PowerAmount(amount = 1,
                                 unit = U2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun minusSamePower()
    {
        assertEquals(
            expected = PowerAmount(amount = 0,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1) -
                     PowerAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun minusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(amount = 0,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1) -
                     PowerAmount(amount = 1,
                                 unit = U2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     PowerAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME2),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     SpeedAmount(amount = 2,
                                unit = LENGTH1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS2, LENGTH2) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH2) to TIME1) /
                     SpeedAmount(amount = 200,
                                 unit = LENGTH1 to TIME2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                    unit = LENGTH1 to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     ForceAmount(amount = 2,
                                unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH2 to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH2) to TIME1) /
                     ForceAmount(amount = 20000,
                                 unit = U2(MASS1, LENGTH1) to TIME2))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun divElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }
}