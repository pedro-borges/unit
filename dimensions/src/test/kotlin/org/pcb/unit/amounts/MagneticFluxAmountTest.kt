package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MagneticFluxAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun plusOtherMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) +
                     MagneticFluxAmount(amount = 1000000,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun minusSameMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 0,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 1,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) -
                     MagneticFluxAmount(amount = 1,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusOtherMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 0,
                                          unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 1,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) -
                     MagneticFluxAmount(amount = 1000000,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameMagneticFlux()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherMagneticFlux()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxAmount(amount = 2000000,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 20000000,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME2)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME2)) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) /
                     ElectricChargeAmount(amount = 200,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesSameInductance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherInductance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     InductanceAmount(amount = 20000000,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)) *
                     ElectricCurrentAmount(amount = 20,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricResistanceAmount(amount = 200000000,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE2, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun divSameMagneticFluxDensity()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxDensityAmount(amount = 20000,
                                               unit = MASS1 to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = InductanceAmount(amount = 1,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxDensityAmount(amount = 20000,
                                               unit = MASS1 to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameInductance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherInductance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     InductanceAmount(amount = 20000000,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }
}