package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.TimeAmount
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
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun plusOtherMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun minusSameMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 0,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 1,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) -
                     MagneticFluxAmount(amount = 1,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun minusOtherMagneticFlux()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 0,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) -
                     MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameMagneticFlux()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherMagneticFlux()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1) / (TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     ElectricTensionAmount(amount = 20,
                                           unit = (MASS2 * LENGTH2) / (TIME2 * ELECTRICCURRENT2)))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 20,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     TimeAmount(amount = 2,
                                unit = TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 200,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = (MASS1 * LENGTH1) / (TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)) /
                     ElectricResistanceAmount(amount = 200,
                                              unit = (MASS2 * LENGTH2) / (TIME2 * ELECTRICCURRENT2)))
    }
}