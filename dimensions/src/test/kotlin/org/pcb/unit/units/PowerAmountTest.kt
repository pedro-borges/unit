package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class PowerAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSamePower()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = PowerAmount(amount = 0,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) +
                     PowerAmount(amount = 1,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun plusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = PowerAmount(amount = 0,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) +
                     PowerAmount(amount = 1000000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun minusSamePower()
    {
        assertEquals(
            expected = PowerAmount(amount = 0,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = PowerAmount(amount = 1,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) -
                     PowerAmount(amount = 1,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun minusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(amount = 0,
                                   unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = PowerAmount(amount = 1,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) -
                     PowerAmount(amount = 1000000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) /
                     PowerAmount(amount = 2000000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     PowerAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME2),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME2),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) /
                     SpeedAmount(amount = 200,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE2 to TIME1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) /
                     ForceAmount(amount = 20000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) /
                     ElectricTensionAmount(amount = 2000000,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun divElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }
}