package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class EnergyDensityAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = EnergyDensityAmount(amount = 2 * 2,
                                           unit = MASS1 to U2(DISTANCE1, TIME1)),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = EnergyDensityAmount(amount = 1,
                                           unit = MASS1 to U2(DISTANCE1, TIME1)),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameEnergyDensity()
    {
        assertEquals(
            expected = EnergyDensityAmount(amount = 1,
                                           unit = MASS1 to U2(DISTANCE1, TIME1)),
            actual = EnergyDensityAmount(amount = 0,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) +
                     EnergyDensityAmount(amount = 1,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)))
    }

    @Test
    fun plusOtherEnergyDensity()
    {
        assertEquals(
            expected = EnergyDensityAmount(amount = 1,
                                           unit = MASS2 to U2(DISTANCE1, TIME1)),
            actual = EnergyDensityAmount(amount = 0,
                                         unit = MASS2 to U2(DISTANCE1, TIME1)) +
                     EnergyDensityAmount(amount = 10000,
                                         unit = MASS1 to U2(DISTANCE2, TIME2)))
    }

    @Test
    fun minusSameEnergyDensity()
    {
        assertEquals(
            expected = EnergyDensityAmount(amount = 0,
                                           unit = MASS1 to U2(DISTANCE1, TIME1)),
            actual = EnergyDensityAmount(amount = 1,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) -
                     EnergyDensityAmount(amount = 1,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)))
    }

    @Test
    fun minusOtherEnergyDensity()
    {
        assertEquals(
            expected = EnergyDensityAmount(amount = 0,
                                           unit = MASS2 to U2(DISTANCE1, TIME1)),
            actual = EnergyDensityAmount(amount = 1,
                                         unit = MASS2 to U2(DISTANCE1, TIME1)) -
                     EnergyDensityAmount(amount = 10000,
                                         unit = MASS1 to U2(DISTANCE2, TIME2)))
    }

    @Test
    fun divSameEnergyDensity()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) /
                     EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)))
    }

    @Test
    fun divOtherEnergyDensity()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS2 to U2(DISTANCE1, TIME1)) /
                     EnergyDensityAmount(amount = 20000,
                                         unit = MASS1 to U2(DISTANCE2, TIME2)))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) *
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE2) to TIME1),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE2, TIME1)) *
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun timesSameVolume()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)) *
                     VolumeAmount(amount = 2,
                                  unit = DISTANCE1))
    }

    @Test
    fun timesOtherVolume()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE2) to TIME1),
            actual = EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE2, TIME1)) *
                     VolumeAmount(amount = 2000,
                                  unit = DISTANCE1))
    }
}