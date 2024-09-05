package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
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

class ElectricChargeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 / 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 0 + 1,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 0,
                                          unit = ELECTRICCURRENT1 * TIME1) +
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun plusOtherElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 0 + (2 * 2),
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 0,
                                          unit = ELECTRICCURRENT1 * TIME1) +
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun minusSameElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 1 - 1,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT1 * TIME1) -
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun minusOtherElectricCharge()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 4 - (2 * 2),
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 4,
                                          unit = ELECTRICCURRENT1 * TIME1) -
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = BigDecimal(4 / (2 * 2)),
            actual = ElectricChargeAmount(amount = 4,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 2 / 2,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                  (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, out Unit<Length>>>(
            expected = ElectricCapacitanceAmount(amount = 2 / 2,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricTensionAmount(amount = 2 * 2,
                                           unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                  (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricCapacitance()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 / 2,
                                             unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                    (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun divOtherElectricCapacitance()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 16 / 16,
                                             unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                    (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2 * (2 * 2 * 2),
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME2 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                      (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                  (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1 * LENGTH1) /
                                           (TIME1 * TIME1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     ElectricTensionAmount(amount = 2 * (2 * 2 * 2 * 2),
                                           unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                  (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                     (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     ElectricResistanceAmount(amount = 2 * (2 * 2),
                                              unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                     (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2)))
    }
}