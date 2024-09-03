package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricChargeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            major = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 / 2,
                                            major = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 0 + 1,
                                            major = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 0,
                                          major = ELECTRICCURRENT1 * TIME1) +
                     ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun plusOtherElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 0 + (2 * 2),
                                            major = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 0,
                                          major = ELECTRICCURRENT1 * TIME1) +
                     ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun minusSameElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 1 - 1,
                                            major = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT1 * TIME1) -
                     ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun minusOtherElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 4 - (2 * 2),
                                            major = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 4,
                                          major = ELECTRICCURRENT1 * TIME1) -
                     ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = NaturalAmount(amount = 1),
            actual = ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1) /
                     ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = NaturalAmount(amount = 4 / (2 * 2)),
            actual = ElectricChargeAmount(amount = 4,
                                          major = ELECTRICCURRENT1 * TIME1) /
                     ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             major = ELECTRICCURRENT1),
            actual = ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1) /
                     TimeAmount(amount = 2,
                                major = TIME1))
    }
}