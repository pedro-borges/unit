package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.MomentumAmount
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class EnergyAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<EnergyAmount<MASS1, out Unit<Length>, out Unit<Time>>>(
            expected = EnergyAmount(amount = 1,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 1,
                                  major = MASS1 * LENGTH1 * LENGTH2,
                                  minor = TIME1 * TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 / 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameEnergy()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 0 + 1,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 0,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) +
                     EnergyAmount(amount = 1,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1))
    }

    @Test
    fun plusOtherEnergy()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 0 + 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 0,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) +
                     EnergyAmount(amount = 1,
                                  major = MASS2 * LENGTH2 * LENGTH2,
                                  minor = TIME2 * TIME2))
    }

    @Test
    fun minusSameEnergy()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 1 - 1,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 1,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) -
                     EnergyAmount(amount = 1,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1))
    }

    @Test
    fun minusOtherEnergy()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 - 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) -
                     EnergyAmount(amount = 1,
                                  major = MASS2 * LENGTH2 * LENGTH2,
                                  minor = TIME2 * TIME2))
    }

    @Test
    fun divSameEnergy()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1))
    }

    @Test
    fun divOtherEnergy()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     EnergyAmount(amount = 1,
                                  major = MASS2 * LENGTH2 * LENGTH2,
                                  minor = TIME2 * TIME2))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    major = LENGTH1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     ForceAmount(amount = 2,
                                 major = MASS1 * LENGTH1,
                                 minor = TIME1 * TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    major = LENGTH1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     ForceAmount(amount = 2,
                                 major = MASS2 * LENGTH2,
                                 minor = TIME2 * TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     MomentumAmount(amount = 1,
                                    major = MASS2 * LENGTH2,
                                    minor = TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 / 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 / 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     SpeedAmount(2, LENGTH2, TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 / 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 / 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = EnergyAmount(amount = 2,
                                  major = MASS1 * LENGTH1 * LENGTH1,
                                  minor = TIME1 * TIME1) /
                     ElectricChargeAmount(amount = 1, major = ELECTRICCURRENT1 * TIME2))
    }
}