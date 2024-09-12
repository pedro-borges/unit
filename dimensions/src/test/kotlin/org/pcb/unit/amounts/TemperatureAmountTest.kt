package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TEMPERATURE1
import org.pcb.unit.units.test.TEMPERATURE2

class TemperatureAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = TemperatureAmount(amount = 2 * 2,
                                         unit = TEMPERATURE1),
            actual = TemperatureAmount(amount = 2,
                                       unit = TEMPERATURE1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = TemperatureAmount(amount = 1,
                                         unit = TEMPERATURE1),
            actual = TemperatureAmount(amount = 2,
                                       unit = TEMPERATURE1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameTemperature()
    {
        assertEquals(
            expected = TemperatureAmount(amount = 1,
                                         unit = TEMPERATURE1),
            actual = TemperatureAmount(amount = 0,
                                       unit = TEMPERATURE1) +
                     TemperatureAmount(amount = 1,
                                       unit = TEMPERATURE1))
    }

    @Test
    fun plusOtherTemperature()
    {
        assertEquals(
            expected = TemperatureAmount(amount = 1,
                                         unit = TEMPERATURE2),
            actual = TemperatureAmount(amount = 0,
                                       unit = TEMPERATURE2) +
                     TemperatureAmount(amount = 10,
                                       unit = TEMPERATURE1))
    }

    @Test
    fun minusSameTemperature()
    {
        assertEquals(
            expected = TemperatureAmount(amount = 0,
                                         unit = TEMPERATURE1),
            actual = TemperatureAmount(amount = 1,
                                       unit = TEMPERATURE1) -
                     TemperatureAmount(amount = 1,
                                       unit = TEMPERATURE1))
    }

    @Test
    fun minusOtherTemperature()
    {
        assertEquals(
            expected = TemperatureAmount(amount = 0,
                                         unit = TEMPERATURE2),
            actual = TemperatureAmount(amount = 1,
                                       unit = TEMPERATURE2) -
                     TemperatureAmount(amount = 10,
                                       unit = TEMPERATURE1))
    }

    @Test
    fun divSameTemperature()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = TemperatureAmount(amount = 2,
                                       unit = TEMPERATURE1) /
                     TemperatureAmount(amount = 2,
                                       unit = TEMPERATURE1))
    }

    @Test
    fun divOtherTemperature()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = TemperatureAmount(amount = 2,
                                       unit = TEMPERATURE2) /
                     TemperatureAmount(amount = 20,
                                       unit = TEMPERATURE1))
    }
}