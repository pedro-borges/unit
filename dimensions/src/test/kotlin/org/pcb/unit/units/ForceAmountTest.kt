package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ForceAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ForceAmount<MASS1, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 1,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME2)))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 / 2,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 0 + 1,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 0,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) +
                     ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 0 + 1,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 0,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) +
                     ForceAmount(amount = 1,
                                 unit = (MASS2 * LENGTH2) /
                                        (TIME2 * TIME2)))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 1 - 1,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) -
                     ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 1 - 1,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = ForceAmount(amount = 1,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) -
                     ForceAmount(amount = 1,
                                 unit = (MASS2 * LENGTH2) /
                                        (TIME2 * TIME2)))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     ForceAmount(amount = 2,
                                 unit = (MASS2 * LENGTH2) /
                                        (TIME2 * TIME2)))
    }

    @Test
    fun divSameMass()
    {
        assertEquals<AccelerationAmount<out Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     MassAmount(amount = 2,
                                unit = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals<AccelerationAmount<out Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     MassAmount(amount = 1,
                                unit = MASS2))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  unit = MASS1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  unit = MASS1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) /
                     AccelerationAmount(amount = 2 * 2,
                                        unit = LENGTH2 /
                                               (TIME2 * TIME2)))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = (MASS1 * LENGTH1) /
                                             TIME1),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) *
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = ForceAmount(amount = 2,
                                 unit = (MASS1 * LENGTH1) /
                                        (TIME1 * TIME1)) *
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }
}