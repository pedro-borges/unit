package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Fraction
import org.pcb.unit.Tuple
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
                                   unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 0,
                                 unit = (MASS1 * LENGTH1) / TIME1) +
                     ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) / TIME1))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 0,
                                 unit = (MASS1 * LENGTH1) / TIME1) +
                     ForceAmount(amount = 1,
                                 unit = (MASS2 * LENGTH2) / TIME2))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 0,
                                   unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) / TIME1) -
                     ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) / TIME1))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(amount = 0,
                                   unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) / TIME1) -
                     ForceAmount(amount = 1,
                                 unit = (MASS2 * LENGTH2) / TIME2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1) /
                     ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1) /
                     ForceAmount(amount = 2,
                                 unit = (MASS2 * LENGTH2) / TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = Fraction(LENGTH1, TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = Tuple(MASS1, LENGTH1) / TIME1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = Fraction(LENGTH1, TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = Tuple(MASS2, LENGTH1) / TIME1) /
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
                                 unit = Tuple(MASS1, LENGTH1) / TIME1) /
                     AccelerationAmount(amount = 2,
                                        unit = LENGTH1 / TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(amount = 1,
                                  unit = MASS1),
            actual = ForceAmount(amount = 2,
                                 unit = Tuple(MASS1, LENGTH1) / TIME1) /
                     AccelerationAmount(amount = 20,
                                        unit = LENGTH2 / TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = (MASS2 * LENGTH2) / TIME2),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS2 * LENGTH2) / TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1) / TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) / TIME1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS2 * LENGTH2) / TIME2),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS2 * LENGTH2) / TIME2) *
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }
}