package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME2

class VolumeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE1),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = VolumeAmount(amount = 1,
                                    unit = DISTANCE1),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 1,
                                    unit = DISTANCE1),
            actual = VolumeAmount(amount = 0,
                                  unit = DISTANCE1) +
                     VolumeAmount(amount = 1,
                                  unit = DISTANCE1))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 1,
                                    unit = DISTANCE2),
            actual = VolumeAmount(amount = 0,
                                  unit = DISTANCE2) +
                     VolumeAmount(amount = 1000,
                                  unit = DISTANCE1))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 0,
                                    unit = DISTANCE1),
            actual = VolumeAmount(amount = 1,
                                  unit = DISTANCE1) -
                     VolumeAmount(amount = 1,
                                  unit = DISTANCE1))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 0,
                                    unit = DISTANCE2),
            actual = VolumeAmount(amount = 1,
                                  unit = DISTANCE2) -
                     VolumeAmount(amount = 1000,
                                  unit = DISTANCE1))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) /
                     VolumeAmount(amount = 2,
                                  unit = DISTANCE1))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE2) /
                     VolumeAmount(amount = 2000,
                                  unit = DISTANCE1))
    }

    @Test
    fun divSameDistance()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) /
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun divOtherDistance()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE2),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE2) /
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE1),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) /
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE2),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE2) /
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun timesSamePressure()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE1) to TIME2),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) *
                     PressureAmount(amount = 2,
                                    unit = MASS2 to U2(DISTANCE1, TIME2)))
    }

    @Test
    fun timesOtherPressure()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE1) to TIME2),
            actual = VolumeAmount(amount = 2,
                                  unit = DISTANCE1) *
                     PressureAmount(amount = 20,
                                    unit = MASS2 to U2(DISTANCE2, TIME2)))
    }
}