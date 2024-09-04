package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MomentumAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 / 2,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 0 + 1,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = MomentumAmount(amount = 0,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) +
                     MomentumAmount(amount = 1,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 0 + 2,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = MomentumAmount(amount = 0,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) +
                     MomentumAmount(amount = 1,
                                    unit = (MASS2 * LENGTH2) /
                                           TIME2))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 1 - 1,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = MomentumAmount(amount = 1,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) -
                     MomentumAmount(amount = 1,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 - 2,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) -
                     MomentumAmount(amount = 1,
                                    unit = (MASS2 * LENGTH2) /
                                           TIME2))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     MomentumAmount(amount = 1,
                                    unit = (MASS2 * LENGTH2) /
                                           TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals<SpeedAmount<out Unit<Length>, TIME1>>(
            expected = SpeedAmount(amount = 2 / 2,
                                   unit = LENGTH1 / TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals<SpeedAmount<out Unit<Length>, TIME1>>(
            expected = SpeedAmount(amount = 2 / 2,
                                   unit = LENGTH1 / TIME1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     MassAmount(amount = 1,
                                unit = MASS2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  unit = MASS1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 / TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  unit = MASS1),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 / TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 / 2,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 1,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) /
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 / TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = MomentumAmount(amount = 2,
                                    unit = (MASS1 * LENGTH1) /
                                           TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 / TIME2))
    }
}