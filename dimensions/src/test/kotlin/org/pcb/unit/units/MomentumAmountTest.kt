package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MomentumAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MomentumAmount(amount = 1,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(amount = 1,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 0,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) +
                     MomentumAmount(amount = 1,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(amount = 1,
                                      unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = MomentumAmount(amount = 0,
                                    unit = U2(MASS2, DISTANCE2) to TIME2) +
                     MomentumAmount(amount = 10,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(amount = 0,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 1,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) -
                     MomentumAmount(amount = 1,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(amount = 0,
                                      unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = MomentumAmount(amount = 1,
                                    unit = U2(MASS2, DISTANCE2) to TIME2) -
                     MomentumAmount(amount = 10,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME2) /
                     MomentumAmount(amount = 20,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS2, DISTANCE1) to TIME1) /
                     MassAmount(amount = 20,
                                unit = MASS1))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, DISTANCE1) to TIME2),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME2) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME2))
    }

    @Test
    fun divSameDistance()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 1,
                                          unit = MASS1 to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     DistanceAmount(amount = 2,
                                  unit = DISTANCE1))
    }

    @Test
    fun divOtherDistance()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 1,
                                          unit = MASS1 to TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE2) to TIME1) /
                     DistanceAmount(amount = 20,
                                  unit = DISTANCE1))
    }

    @Test
    fun divSameMassFlux()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                    unit = DISTANCE1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1) /
                     MassFluxAmount(amount = 2,
                                        unit = MASS1 to TIME1))
    }

    @Test
    fun divOtherMassFlux()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                    unit = DISTANCE1),
            actual = MomentumAmount(amount = 2,
                                    unit = U2(MASS2, DISTANCE1) to TIME1) /
                     MassFluxAmount(amount = 200,
                                        unit = MASS1 to TIME2))
    }
}