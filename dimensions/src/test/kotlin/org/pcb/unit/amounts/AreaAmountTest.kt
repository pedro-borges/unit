package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1

class AreaAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 0,
                                unit = DISTANCE1) +
                     AreaAmount(amount = 1,
                                unit = DISTANCE1))
    }

    @Test
    fun plusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE2),
            actual = AreaAmount(amount = 0,
                                unit = DISTANCE2) +
                     AreaAmount(amount = 100,
                                unit = DISTANCE1))
    }

    @Test
    fun minusSameArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 0,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 1,
                                unit = DISTANCE1) -
                     AreaAmount(amount = 1,
                                unit = DISTANCE1))
    }

    @Test
    fun minusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 0,
                                  unit = DISTANCE2),
            actual = AreaAmount(amount = 1,
                                unit = DISTANCE2) -
                     AreaAmount(amount = 100,
                                unit = DISTANCE1))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) /
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) /
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun divSameDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) /
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun divOtherDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE2),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) /
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameDistance()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesOtherDistance()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE2),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) *
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesSameEnergyDensity()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     EnergyDensityAmount(amount = 2,
                                         unit = MASS1 to U2(DISTANCE1, TIME1)))
    }

    @Test
    fun timesOtherEnergyDensity()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     EnergyDensityAmount(amount = 20,
                                         unit = MASS1 to U2(DISTANCE2, TIME1)))
    }

    @Test
    fun timesSameIlluminance()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 2 * 2,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1)),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     IlluminanceAmount(amount = 2,
                                       unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1))
    }

    @Test
    fun timesOtherIlluminance()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 2 * 2,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1)),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) *
                     IlluminanceAmount(amount = 200,
                                       unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1))
    }

    @Test
    fun timesSameLuminance()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 2 * 2,
                                               unit = LUMINOUSINTENSITY1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }

    @Test
    fun timesOtherLuminance()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 2 * 2,
                                               unit = LUMINOUSINTENSITY1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) *
                     LuminanceAmount(amount = 200,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }
}