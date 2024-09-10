package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
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
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     Percent(amount = 2 * 100))
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
            expected = Numeric(amount = 1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = TimeAmount(amount = 1,
                                unit = TIME2) /
                     TimeAmount(amount = 10,
                                unit = TIME1))
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
    fun timesSamePower()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     PowerAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun timesOtherPower()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     PowerAmount(amount = 2000,
                                 unit = U2(MASS1, DISTANCE1) to TIME2))
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
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT2) to U2(MASS2, DISTANCE2)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricResistanceAmount(amount = 2000,
                                              unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricConductivity()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherElectricConductivity()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME2) *
                     ElectricConductivityAmount(amount = 2000,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divSameElectricCapacitance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherElectricCapacitance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)),
            actual = TimeAmount(amount = 2,
                                unit = TIME2) /
                     ElectricCapacitanceAmount(amount = 20000,
                                               unit = U2(TIME1, ELECTRICCURRENT2) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS1, DISTANCE1) to U2(TIME2, ELECTRICCURRENT1)),
            actual = TimeAmount(amount = 2,
                                unit = TIME2) *
                     ElectricTensionAmount(amount = 2000,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }
}
