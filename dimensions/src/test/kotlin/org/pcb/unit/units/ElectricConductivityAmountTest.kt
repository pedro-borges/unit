package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricConductivityAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 2 * 2,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 0,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) +
                     ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun plusOtherElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 0,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) +
                     ElectricConductivityAmount(amount = 100000000,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun minusSameElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 0,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) -
                     ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun minusOtherElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 0,
                                                  unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) -
                     ElectricConductivityAmount(amount = 100000000,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun divSameElectricConductivity()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherElectricConductivity()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) /
                     ElectricConductivityAmount(amount = 200000000,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = Numeric(amount = 2 * 2),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = Numeric(amount = 2 * 2),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) *
                     ElectricResistanceAmount(amount = 200000000,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) *
                     ElectricTensionAmount(amount = 20000000,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     FrequencyAmount(amount = 20,
                                     unit = NUMERIC to TIME1))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameCapacitance()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherCapacitance()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) /
                     CapacitanceAmount(amount = 2000000000,
                                       unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)))
    }
}