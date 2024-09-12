package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricCurrentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 0,
                                           unit = ELECTRICCURRENT1) +
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT2),
            actual = ElectricCurrentAmount(amount = 0,
                                           unit = ELECTRICCURRENT2) +
                     ElectricCurrentAmount(amount = 10,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun minusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 0,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT1) -
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 0,
                                             unit = ELECTRICCURRENT2),
            actual = ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT2) -
                     ElectricCurrentAmount(amount = 10,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT2) /
                     ElectricCurrentAmount(amount = 20,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesTime()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricResistanceAmount(amount = 200,
                                              unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricConductance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun divOtherElectricConductance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricConductanceAmount(amount = 200,
                                               unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricTensionAmount(amount = 20,
                                           unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricTensionAmount(amount = 20,
                                           unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameMagneticFlux()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherMagneticFlux()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     MagneticFluxAmount(amount = 20,
                                        unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameInductance()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     InductanceAmount(amount = 2,
                                      unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherInductance()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     InductanceAmount(amount = 200,
                                      unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }
}