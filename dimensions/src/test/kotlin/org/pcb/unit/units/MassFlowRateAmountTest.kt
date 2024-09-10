package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MassFlowRateAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 2 * 2,
                                   unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 2,
                                 unit = MASS1 to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 1,
                                   unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 2,
                                 unit = MASS1 to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMassFlowRate()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 1,
                                   unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 0,
                                 unit = MASS1 to TIME1) +
                     MassFlowRateAmount(amount = 1,
                                 unit = MASS1 to TIME1))
    }

    @Test
    fun plusOtherMassFlowRate()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 0 + (2 / 2),
                                   unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 0,
                                 unit = MASS1 to TIME1) +
                     MassFlowRateAmount(amount = 1,
                                 unit = MASS2 to TIME2))
    }

    @Test
    fun minusSameMassFlowRate()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 0,
                                   unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 1,
                                 unit = MASS1 to TIME1) -
                     MassFlowRateAmount(amount = 1,
                                 unit = MASS1 to TIME1))
    }

    @Test
    fun minusOtherMassFlowRate()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 1 - (2 / 2),
                                   unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 1,
                                 unit = MASS1 to TIME1) -
                     MassFlowRateAmount(amount = 1,
                                 unit = MASS2 to TIME2))
    }

    @Test
    fun divSameMassFlowRate()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MassFlowRateAmount(amount = 2,
                                 unit = MASS1 to TIME1) /
                     MassFlowRateAmount(amount = 2,
                                 unit = MASS1 to TIME1))
    }

    @Test
    fun divOtherMassFlowRate()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MassFlowRateAmount(amount = 2,
                                 unit = MASS1 to TIME1) /
                     MassFlowRateAmount(amount = 2,
                                 unit = MASS2 to TIME2))
    }
}
