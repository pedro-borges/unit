package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.MomentumAmount
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.SpeedAmount
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

class EnergyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = EnergyAmount(amount = 1,
                                    unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameEnergy()
    {
        assertEquals(
            expected = EnergyAmount(amount = 1,
                                    unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 0,
                                  unit = T2(MASS1, LENGTH1) to TIME1) +
                     EnergyAmount(amount = 1,
                                  unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun plusOtherEnergy()
    {
        assertEquals(
            expected = EnergyAmount(amount = 1,
                                    unit = T2(MASS2, LENGTH2) to TIME2),
            actual = EnergyAmount(amount = 0,
                                  unit = T2(MASS2, LENGTH2) to TIME2) +
                     EnergyAmount(amount = 10,
                                  unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun minusSameEnergy()
    {
        assertEquals(
            expected = EnergyAmount(amount = 0,
                                    unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 1,
                                  unit = T2(MASS1, LENGTH1) to TIME1) -
                     EnergyAmount(amount = 1,
                                  unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun minusOtherEnergy()
    {
        assertEquals(
            expected = EnergyAmount(amount = 0,
                                    unit = T2(MASS2, LENGTH2) to TIME2),
            actual = EnergyAmount(amount = 1,
                                  unit = T2(MASS2, LENGTH2) to TIME2) -
                     EnergyAmount(amount = 10,
                                  unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divSameEnergy()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherEnergy()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS2, LENGTH2) to TIME2) /
                     EnergyAmount(amount = 20,
                                  unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = ForceAmount(amount = 1,
                                   unit = T2(MASS2, LENGTH2) to TIME2),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS2, LENGTH2) to TIME2) /
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     ForceAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     ForceAmount(amount = 2,
                                 unit = T2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     PowerAmount(amount = 2,
                                 unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     PowerAmount(amount = 2,
                                 unit = T2(MASS2, LENGTH2) to TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = PowerAmount(amount = 1,
                                   unit = T2(MASS1, LENGTH1) to TIME2),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME2) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH1 to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     MomentumAmount(amount = 2,
                                    unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH2 to TIME2),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS2, LENGTH2) to TIME2) /
                     MomentumAmount(amount = 20,
                                    unit = T2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MomentumAmount(amount = 1,
                                      unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MomentumAmount(amount = 1,
                                      unit = T2(MASS1, LENGTH1) to TIME1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 to TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     ElectricChargeAmount(amount = 2,
                                          unit = T2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = T2(MASS2, LENGTH2) to T2(TIME2, ELECTRICCURRENT2)),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS2, LENGTH2) to TIME2) /
                     ElectricChargeAmount(amount = 20,
                                          unit = T2(ELECTRICCURRENT2, TIME1)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = T2(ELECTRICCURRENT1, TIME1)),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = T2(ELECTRICCURRENT2, TIME1)),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           unit = T2(MASS2, LENGTH2) to T2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divElectricCurrent()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 1,
                                          unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divSameMagneticFlux()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS1, LENGTH1) to TIME1) /
                     MagneticFluxAmount(amount = 2,
                                        unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherMagneticFlux()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT2),
            actual = EnergyAmount(amount = 2,
                                  unit = T2(MASS2, LENGTH2) to TIME2) /
                     MagneticFluxAmount(amount = 20,
                                        unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT2)))
    }
}