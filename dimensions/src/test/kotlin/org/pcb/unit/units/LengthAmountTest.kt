package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class LengthAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 0,
                                  unit = LENGTH1) +
                     LengthAmount(amount = 1,
                                  unit = LENGTH1))
    }

    @Test
    fun plusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH2),
            actual = LengthAmount(amount = 0,
                                  unit = LENGTH2) +
                     LengthAmount(amount = 10,
                                  unit = LENGTH1))
    }

    @Test
    fun minusSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 0,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 1,
                                  unit = LENGTH1) -
                     LengthAmount(amount = 1,
                                  unit = LENGTH1))
    }

    @Test
    fun minusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 0, LENGTH2),
            actual = LengthAmount(amount = 1,
                                  unit = LENGTH2) -
                     LengthAmount(amount = 10,
                                  unit = LENGTH1))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH2) /
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH1 to TIME1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = LENGTH2),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH2) *
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     AreaAmount(amount = 2,
                                unit = LENGTH1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = LENGTH2),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH2) *
                     AreaAmount(amount = 200,
                                unit = LENGTH1))
    }

    @Test
    fun timesSameForce()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun timesOtherForce()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, LENGTH2) to TIME2),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH2) *
                     ForceAmount(amount = 20,
                                 unit = U2(MASS2, LENGTH1) to TIME2))
    }

    @Test
    fun timesMassFlux()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1))
    }
}
