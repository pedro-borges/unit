package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.MagneticFluxAmount
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
    fun construct()
    {
        assertEquals<MagneticFluxAmount<MASS1, out Unit<Length>, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH2) /
                                               (TIME1 * TIME2 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2 / 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMagneticFlux()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 0 + 1,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun plusOtherMagneticFlux()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 0 + 1,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 0,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) +
                     MagneticFluxAmount(amount = 1,
                                        unit = (MASS2 * LENGTH2 * LENGTH2) /
                                               (TIME2 * TIME2 * ELECTRICCURRENT2)))
    }

    @Test
    fun minusSameMagneticFlux()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2 - 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 1,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) -
                     MagneticFluxAmount(amount = 1,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun minusOtherMagneticFlux()
    {
        assertEquals<MagneticFluxAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = MagneticFluxAmount(amount = 2 - 2,
                                          unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                 (TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) -
                     MagneticFluxAmount(amount = 2,
                                        unit = (MASS2 * LENGTH2 * LENGTH2) /
                                               (TIME2 * TIME2 * ELECTRICCURRENT2)))
    }

    @Test
    fun divSameMagneticFlux()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherMagneticFlux()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = MagneticFluxAmount(amount = 2,
                                        unit = (MASS1 * LENGTH1 * LENGTH1) /
                                               (TIME1 * TIME1 * ELECTRICCURRENT1)) /
                     MagneticFluxAmount(amount = 2,
                                        unit = (MASS2 * LENGTH2 * LENGTH2) /
                                               (TIME2 * TIME2 * ELECTRICCURRENT2)))
    }
}