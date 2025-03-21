package org.pcb.unit

import org.pcb.unit.Molecules.CH4
import org.pcb.unit.Molecules.CO
import org.pcb.unit.Molecules.CO2
import org.pcb.unit.Molecules.H2
import org.pcb.unit.Molecules.I2
import org.pcb.unit.Molecules.N2
import org.pcb.unit.Molecules.N2O
import org.pcb.unit.Molecules.NH3
import org.pcb.unit.Molecules.NO2
import org.pcb.unit.Molecules.O2
import org.pcb.unit.Molecules.O3
import org.pcb.unit.amounts.MolarMassAmount
import org.pcb.unit.amounts.thermodynamics.Gas
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Mole.MOLE
import org.pcb.unit.chemisty.ChemicalElement.Ar
import org.pcb.unit.chemisty.ChemicalElement.He
import org.pcb.unit.chemisty.ChemicalElement.Kr
import org.pcb.unit.chemisty.ChemicalElement.Ne
import org.pcb.unit.chemisty.ChemicalElement.Xe

object Earth
{
    // Data from https://www.noaa.gov/jetstream/atmosphere
    // TODO it's inaccurate as the first four elements alone already add up to more than 100%
    val air = mapOf(N2 to 0.78084,
                    O2 to 0.20946,
                    Ar to 0.00934,
                    CO2 to 0.00042,
                    Ne to 0.000018182,
                    He to 0.00000524,
                    CH4 to 0.00000192,
                    Kr to 0.00000114,
                    H2 to 0.00000055,
                    N2O to 0.00000033,
                    CO to 0.00000010,
                    Xe to 0.00000009,
                    O3 to 0.00000007,
                    NO2 to 0.00000002,
                    I2 to 0.00000001,
                    NH3 to 0.000000001)

    val airAtomicMass =
        air.map { it.key.atomicMass * it.value }
            .reduce(MolarMassAmount<GRAM, MOLE>::plus)

    val gasConstant = Gas(8.31446261815324)
}