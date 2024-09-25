package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class TimeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = TimeAmount(amount = 0,
                                unit = TIME1) +
                     TimeAmount(amount = 1,
                                unit = TIME1))
    }

    @Test
    fun plusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME2),
            actual = TimeAmount(amount = 0,
                                unit = TIME2) +
                     TimeAmount(amount = 10,
                                unit = TIME1))
    }

    @Test
    fun minusSameTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 0,
                                  unit = TIME1),
            actual = TimeAmount(amount = 1,
                                unit = TIME1) -
                     TimeAmount(amount = 1,
                                unit = TIME1))
    }

    @Test
    fun minusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 0,
                                  unit = TIME2),
            actual = TimeAmount(amount = 1,
                                unit = TIME2) -
                     TimeAmount(amount = 10,
                                unit = TIME1))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = TimeAmount(amount = 1,
                                unit = TIME2) /
                     TimeAmount(amount = 10,
                                unit = TIME1))
    }

    @Test
    fun timesSameFrequency()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     FrequencyAmount(amount = 20,
                                     unit = NUMBER1 to TIME2))
    }

    @Test
    fun divDistance()
    {
        assertEquals(
            expected = PaceAmount(amount = 1,
                                  unit = TIME1 to DISTANCE1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameMassFlowRate()
    {
        assertEquals(
            expected = MassAmount(amount = 2 * 2,
                                  unit = MASS1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     MassFlowRateAmount(amount = 2,
                                        unit = MASS1 to TIME1))
    }

    @Test
    fun timesOtherMassFlowRate()
    {
        assertEquals(
            expected = MassAmount(amount = 2 * 2,
                                  unit = MASS1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     MassFlowRateAmount(amount = 20,
                                        unit = MASS1 to TIME2))
    }

    @Test
    fun timesSamePower()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     PowerAmount(amount = 2,
                                 unit = U2(DISTANCE1, MASS1) to TIME1))
    }

    @Test
    fun timesOtherPower()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     PowerAmount(amount = 2000,
                                 unit = U2(DISTANCE1, MASS1) to TIME2))
    }

    @Test
    fun timesElectricCurrent()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT2) to U2(MASS2, DISTANCE2)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricResistanceAmount(amount = 2000,
                                              unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricConductance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherElectricConductance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME2) *
                     ElectricConductanceAmount(amount = 2000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divSameCapacitance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherCapacitance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT2, TIME2)),
            actual = TimeAmount(amount = 2,
                                unit = TIME2) /
                     CapacitanceAmount(amount = 20000,
                                       unit = U2(TIME1, ELECTRICCURRENT2) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricTensionAmount(amount = 2000,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME2)))
    }

    @Test
    fun timesLuminousFlux()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 2 * 2,
                                            unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     LuminousFluxAmount(amount = 2,
                                        unit = U2(ANGLE1, LUMINOUSINTENSITY1)))
    }
}
