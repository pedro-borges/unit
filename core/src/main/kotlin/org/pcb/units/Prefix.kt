package org.pcb.units

import java.math.BigDecimal
import org.pcb.units.domain.Domain

@Suppress("unused")
class Quecto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "q${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("100000000000000000000000000000")
}

@Suppress("unused")
class Ronto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "r${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000000000000000000000")
}

@Suppress("unused")
class Yocto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "y${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000000000000000000")
}

@Suppress("unused")
class Zepto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "z${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000000000000000")
}

@Suppress("unused")
class Atto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "a${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000000000000")
}

@Suppress("unused")
class Femto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "f${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000000000")
}

@Suppress("unused")
class Pico<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "p${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000000")
}

@Suppress("unused")
class Nano<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "n${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000000")
}

@Suppress("unused")
class Micro<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "µ${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000000")
}

@Suppress("unused")
class Milli<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "m${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("1000")
}

@Suppress("unused")
class Centi<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "c${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("100")
}

@Suppress("unused")
class Deci<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "d${unit.symbol}"
    override val cannon = unit.cannon / BigDecimal("10")
}

@Suppress("unused")
class Deca<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "da${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("10")
}

@Suppress("unused")
class Hecto<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "h${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("100")
}

@Suppress("unused")
class Kilo<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "k${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000")
}

@Suppress("unused")
class Mega<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "M${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000")
}

@Suppress("unused")
class Giga<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "G${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000")
}

@Suppress("unused")
class Tera<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "G${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000")
}

@Suppress("unused")
class Peta<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "P${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000000")
}

@Suppress("unused")
class Exa<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "H${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000000000")
}

@Suppress("unused")
class Zetta<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "Z${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000000000000")
}

@Suppress("unused")
class Yotta<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "Y${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000000000000000")
}

@Suppress("unused")
class Ronna<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "R${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000000000000000000")
}

@Suppress("unused")
class Quetta<D : Domain>(unit: Unit<D>) : Unit<D>(unit.domain)
{
    override val symbol = "Q${unit.symbol}"
    override val cannon = unit.cannon * BigDecimal("1000000000000000000000000000000")
}
