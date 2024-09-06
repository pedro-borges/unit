package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
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
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     Percent(amount = 2 * 100))
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
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
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
                                             unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricResistanceAmount(amount = 200,
                                              unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricConductivity()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)))
    }

    @Test
    fun divOtherElectricConductivity()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricConductivityAmount(amount = 200,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, LENGTH2)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS2, LENGTH2)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricTensionAmount(amount = 20,
                                           unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS2, LENGTH2) to TIME2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricTensionAmount(amount = 20,
                                           unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameMagneticFlux()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     MagneticFluxAmount(amount = 2,
                                        unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherMagneticFlux()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, LENGTH2) to TIME2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     MagneticFluxAmount(amount = 20,
                                        unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }
}