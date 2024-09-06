package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.AccelerationAmount
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.MomentumAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
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
            expected = BigDecimal(1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = MassAmount(amount = 2,
                                unit = MASS2) /
                     MassAmount(amount = 20,
                                unit = MASS1))
    }

    @Test
    fun timesSpeed()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = Tuple(MASS1, LENGTH1) / TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) *
                     SpeedAmount(amount = 2,
                                 unit = Fraction(LENGTH1, TIME1)))
    }

    @Test
    fun timesAcceleration()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = Tuple(MASS1, LENGTH1) / TIME1),
            actual = MassAmount(amount = 2,
                                unit = MASS1) *
                     AccelerationAmount(amount = 2,
                                        unit = Fraction(LENGTH1, TIME1)))
    }
}