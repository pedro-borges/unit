package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1

class MassAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MassAmount(amount = 2 * 2,
                                  unit = MASS1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMass()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = MassAmount(amount = 0,
                                unit = MASS1) +
                     MassAmount(amount = 1,
                                unit = MASS1))
    }

    @Test
    fun plusOtherMass()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS2),
            actual = MassAmount(amount = 0,
                                unit = MASS2) +
                     MassAmount(amount = 10,
                                unit = MASS1))
    }

    @Test
    fun minusSameMass()
    {
        assertEquals(
            expected = MassAmount(amount = 0,
                                  unit = MASS1),
            actual = MassAmount(amount = 1,
                                unit = MASS1) -
                     MassAmount(amount = 1,
                                unit = MASS1))
    }

    @Test
    fun minusOtherMass()
    {
        assertEquals(
            expected = MassAmount(amount = 0,
                                  unit = MASS2),
            actual = MassAmount(amount = 1,
                                unit = MASS2) -
                     MassAmount(amount = 10,
                                unit = MASS1))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = MassAmount(amount = 2,
                                unit = MASS2) /
                     MassAmount(amount = 20,
                                unit = MASS1))
    }

    @Test
    fun divSameMassFlowRate()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) /
                     MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1))
    }

    @Test
    fun divOtherMassFlowRate()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS2) /
                     MassFlowRateAmount(amount = 20,
                                        unit = MASS1 to TIME1))
    }

    @Test
    fun timesSpeed()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) *
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun timesAcceleration()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) *
                     AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 1,
                                          unit = MASS1 to TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }
}