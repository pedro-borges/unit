package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ForceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 0,
                                 unit = U2(MASS1, LENGTH1) to TIME1) +
                     ForceAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 0,
                                 unit = U2(MASS1, LENGTH1) to TIME1) +
                     ForceAmount(amount = 1,
                                 unit = U2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 0,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1) -
                     ForceAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 0,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 1,
                                 unit = U2(MASS1, LENGTH1) to TIME1) -
                     ForceAmount(amount = 1,
                                 unit = U2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     ForceAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH1 to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH1 to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH1) to TIME1) /
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
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     AccelerationAmount(amount = 2,
                                        unit = LENGTH1 to TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) /
                     AccelerationAmount(amount = 20,
                                        unit = LENGTH2 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS2, LENGTH2) to TIME2),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH2) to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, LENGTH2) to TIME2),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS2, LENGTH2) to TIME2) *
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH1) to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, LENGTH2) to TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = U2(MASS1, LENGTH2) to TIME1) *
                     SpeedAmount(amount = 200,
                                 unit = LENGTH1 to TIME2))
    }
}