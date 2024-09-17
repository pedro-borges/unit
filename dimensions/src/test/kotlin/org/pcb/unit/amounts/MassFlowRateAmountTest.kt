package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
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
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassFlowRateAmount(amount = 1,
                                          unit = MASS1 to TIME1),
            actual = MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
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
            expected = MassFlowRateAmount(amount = 1,
                                          unit = MASS2 to TIME1),
            actual = MassFlowRateAmount(amount = 0,
                                        unit = MASS2 to TIME1) +
                     MassFlowRateAmount(amount = 100,
                                        unit = MASS1 to TIME2))
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
            expected = MassFlowRateAmount(amount = 0,
                                          unit = MASS2 to TIME1),
            actual = MassFlowRateAmount(amount = 1,
                                        unit = MASS2 to TIME1) -
                     MassFlowRateAmount(amount = 100,
                                        unit = MASS1 to TIME2))
    }

    @Test
    fun divSameMassFlowRate()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1) /
                     MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1))
    }

    @Test
    fun divOtherMassFlowRate()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = MassFlowRateAmount(amount = 2,
                                        unit = MASS2 to TIME1) /
                     MassFlowRateAmount(amount = 200,
                                        unit = MASS1 to TIME2))
    }

    @Test
    fun timesDistance()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1) *
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MassAmount(amount = 2 * 2,
                                  unit = MASS1),
            actual = MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MassAmount(amount = 2 * 2,
                                  unit = MASS1),
            actual = MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }
}
