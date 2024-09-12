package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.NUMBER1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class FrequencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 * 2,
                                       unit = NUMBER1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = NUMBER1 to TIME1) +
                     FrequencyAmount(amount = 1,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = NUMBER1 to TIME1) +
                     FrequencyAmount(amount = 10,
                                     unit = NUMBER1 to TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0,
                                       unit = NUMBER1 to TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = NUMBER1 to TIME1) -
                     FrequencyAmount(amount = 1,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0,
                                       unit = NUMBER1 to TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = NUMBER1 to TIME1) -
                     FrequencyAmount(amount = 10,
                                     unit = NUMBER1 to TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) /
                     FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) /
                     FrequencyAmount(amount = 20,
                                     unit = NUMBER1 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = Numeric(amount = 2 * 2),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = Numeric(amount = 2 * 2),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesDistance()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     SpeedAmount(amount = 20,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME2) *
                     DebtAmount(amount = 20,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }

    @Test
    fun timesSameCapacitance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1) *
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherCapacitance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME2) *
                     CapacitanceAmount(amount = 20000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }
}