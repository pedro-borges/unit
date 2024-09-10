package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MassFluxAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 2 * 2,
                                   unit = MASS1 to TIME1),
            actual = MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 1,
                                   unit = MASS1 to TIME1),
            actual = MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMassFlux()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 1,
                                   unit = MASS1 to TIME1),
            actual = MassFluxAmount(amount = 0,
                                 unit = MASS1 to TIME1) +
                     MassFluxAmount(amount = 1,
                                 unit = MASS1 to TIME1))
    }

    @Test
    fun plusOtherMassFlux()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 0 + (2 / 2),
                                   unit = MASS1 to TIME1),
            actual = MassFluxAmount(amount = 0,
                                 unit = MASS1 to TIME1) +
                     MassFluxAmount(amount = 1,
                                 unit = MASS2 to TIME2))
    }

    @Test
    fun minusSameMassFlux()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 0,
                                   unit = MASS1 to TIME1),
            actual = MassFluxAmount(amount = 1,
                                 unit = MASS1 to TIME1) -
                     MassFluxAmount(amount = 1,
                                 unit = MASS1 to TIME1))
    }

    @Test
    fun minusOtherMassFlux()
    {
        assertEquals(
            expected = MassFluxAmount(amount = 1 - (2 / 2),
                                   unit = MASS1 to TIME1),
            actual = MassFluxAmount(amount = 1,
                                 unit = MASS1 to TIME1) -
                     MassFluxAmount(amount = 1,
                                 unit = MASS2 to TIME2))
    }

    @Test
    fun divSameMassFlux()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1) /
                     MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1))
    }

    @Test
    fun divOtherMassFlux()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1) /
                     MassFluxAmount(amount = 2,
                                 unit = MASS2 to TIME2))
    }

    @Test
    fun timesLength()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = MassFluxAmount(amount = 2,
                                 unit = MASS1 to TIME1) *
                     LengthAmount(amount = 2,
                                 unit = LENGTH1))
    }
}
