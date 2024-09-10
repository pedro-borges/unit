package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.AreaAmount
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.MagneticFluxDensityAmount
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MagneticFluxAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun plusOtherMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun minusSameMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 0,
                                          unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 1,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) -
                     MagneticFluxAmount(amount = 1,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun minusOtherMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 0,
                                          unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) -
                     MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameMagneticFlux()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherMagneticFlux()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     ElectricTensionAmount(amount = 20,
                                           unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 20,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     TimeAmount(amount = 2,
                                unit = TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 200,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameEnergy()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) *
                     EnergyAmount(amount = 2,
                                  unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun timesOtherEnergy()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS2, LENGTH2) to U2(TIME1, ELECTRICCURRENT1)) *
                     EnergyAmount(amount = 200000,
                                  unit = U2(MASS1, LENGTH1) to TIME2))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT2)) *
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
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     ElectricResistanceAmount(amount = 200,
                                              unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     AreaAmount(amount = 2,
                                unit = LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH2) to U2(TIME1, ELECTRICCURRENT1)) /
                     AreaAmount(amount = 200,
                                unit = LENGTH1))
    }

    @Test
    fun divSameMagneticFluxDensity()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS2, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxDensityAmount(amount = 20000,
                                               unit = MASS1 to U2(TIME2, ELECTRICCURRENT2)))
    }
}