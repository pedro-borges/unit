package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ForceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 0,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) +
                     ForceAmount(amount = 1,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = ForceAmount(amount = 0,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) +
                     ForceAmount(amount = 10000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 0,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 1,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) -
                     ForceAmount(amount = 1,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 0,
                                   unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = ForceAmount(amount = 1,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) -
                     ForceAmount(amount = 10000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME1) /
                     ForceAmount(amount = 20000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE1) to TIME1) /
                     MassAmount(amount = 20,
                                unit = MASS1))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE2) to TIME1) /
                     AccelerationAmount(amount = 2000,
                                        unit = DISTANCE1 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesSameDistance()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) *
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesOtherDistance()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE2) to TIME2) *
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE2) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE2) to TIME1) *
                     SpeedAmount(amount = 200,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = PressureAmount(amount = 1,
                                      unit = MASS1 to U2(DISTANCE1, TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = PressureAmount(amount = 1,
                                      unit = MASS1 to U2(DISTANCE2, TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE2) to TIME1) /
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun divSamePressure()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1) /
                     PressureAmount(amount = 2,
                                    unit = MASS1 to U2(DISTANCE1, TIME1)))
    }

    @Test
    fun divOtherPressure()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, DISTANCE1) to TIME1) /
                     PressureAmount(amount = 20000,
                                    unit = MASS1 to U2(DISTANCE2, TIME2)))
    }
}