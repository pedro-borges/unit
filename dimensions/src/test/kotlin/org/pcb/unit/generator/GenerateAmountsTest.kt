package org.pcb.unit.generator

import java.io.File
import kotlin.test.Ignore
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.generator.Dimensions.DIMENSIONS
import org.pcb.unit.generator.Dimensions.Definition
import org.pcb.unit.minus
import org.pcb.unit.plus

@Ignore
class GenerateAmountsTest
{
    companion object
    {
        @JvmStatic
        fun getData() = DIMENSIONS
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getData")
    fun generate(dimension: Definition)
    {
        if (dimension.name == "Scalar") return

        val regions = HashMap<String, String>()

        var code = regions[dimension.name] ?: ""
        val major = dimension.composite.positives()
        val minor = dimension.composite.negatives()

        code += "\toverride operator fun <S : Unit<Scalar>> times(that: Number) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount * that.toBigDecimal(),"
        code += "\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toverride operator fun <S : Unit<Scalar>> div(that: Number) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount / that.toBigDecimal(),"
        code += "\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toverride operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount * that.amount * convert(that.scalar to NUMERIC),"
        code += "\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toverride operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount / that.amount / convert(that.scalar to NUMERIC),"
        code += "\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\t@JvmName(\"plus${dimension.name}\")\n"
        code += "\toperator fun ${dimension.composite.buildGenericDeclaration()} plus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount + that.amount"
        if (major.count() > 0) code += "\n\t\t* convert(${major.buildConvert()})"
        if (minor.count() > 0) code += "\n\t\t/ convert(${minor.buildConvert()})"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toperator fun plus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount + that.amount"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\t@JvmName(\"minus${dimension.name}\")\n"
        code += "\toperator fun ${dimension.composite.buildGenericDeclaration()} minus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount - that.amount"
        if (major.count() > 0) code += "\n\t\t* convert(${major.buildConvert()})"
        if (minor.count() > 0) code += "\n\t\t/ convert(${minor.buildConvert()})"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toperator fun  minus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount - that.amount"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        regions[dimension.name] = code

        DIMENSIONS
            .sortedBy { it.name }
            .forEach { resultDimension ->
                DIMENSIONS
                    .filter { it.name != "Scalar" }
                    .sortedBy { it.name }
                    .forEach { parameterDimension ->
                        code = regions[resultDimension.name] ?: ""

                        if (dimension.composite - parameterDimension.composite == resultDimension.composite)
                        {
                            val keep = parameterDimension.composite.keep(dimension.composite)
                            if (keep.count() > 0)
                            {
                                code += "\t@JvmName(\"div${parameterDimension.name}\")\n"
                                code += "\toperator fun ${parameterDimension.composite.buildGenericDeclaration()} div(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                                code += "\t\t${resultDimension.name}Amount(amount = amount / that.amount"
                                if (keep.positives().count() > 0) code += "\n\t\t/ convert(${keep.positives().buildConvert()})"
                                if (keep.negatives().count() > 0) code += "\n\t\t* convert(${keep.negatives().buildConvert()})"
                                code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                            }

                            val drop = parameterDimension.composite.drop(dimension.composite)
                            code += "\toperator fun "
                            if (drop.count() > 0) code += drop.buildGenericDeclaration()
                            code += " div(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                            code += "\t\t${resultDimension.name}Amount(amount = amount / that.amount"
                            code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                        }
                        if (dimension.composite + parameterDimension.composite == resultDimension.composite)
                        {
                            val keep = parameterDimension.composite.keep(dimension.composite)
                            if (keep.count() > 0)
                            {
                                code += "\t@JvmName(\"times${parameterDimension.name}\")\n"
                                code += "\toperator fun ${parameterDimension.composite.buildGenericDeclaration()} times(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                                code += "\t\t${resultDimension.name}Amount(amount = amount * that.amount"
                                if (keep.positives().count() > 0) code += "\n\t\t* convert(${keep.positives().buildConvert()})"
                                if (keep.negatives().count() > 0) code += "\n\t\t/ convert(${keep.negatives().buildConvert()})"
                                code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                            }

                            val drop = parameterDimension.composite.drop(dimension.composite)
                            code += "\toperator fun "
                            if (drop.count() > 0) code += drop.buildGenericDeclaration()
                            code += " times(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                            code += "\t\t${resultDimension.name}Amount(amount = amount * that.amount"
                            code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                        }

                        if (code != "") regions[resultDimension.name] = code
                    }
            }

        val operatorCode = regions.keys
            .asSequence()
            .sorted()
            .sortedBy { it != "Scalar" }
            .sortedBy { it != dimension.name }
            .map {
                "\t// region -> ${it}\n\n" + regions[it] + "\t// endregion"
            }
            .joinToString(separator = "\n\n")

        val contents =
            "package org.pcb.unit.amounts\n" +
            "\n" +
            "import org.pcb.unit.Amount\n" +
            "import org.pcb.unit.CompositeUnit\n" +
            "import org.pcb.unit.U2\n" +
            "import org.pcb.unit.U3\n" +
            "import org.pcb.unit.Unit\n" +
            "import org.pcb.unit.convert\n" +
            DIMENSIONS.joinToString("") { "import org.pcb.unit.dimensions.${it.name}\n" } +
            "import org.pcb.unit.base.Radian.RADIAN\n" +
            "import org.pcb.unit.base.Mole.MOLE\n" +
            "import org.pcb.unit.base.Ampere.AMPERE\n" +
            "import org.pcb.unit.base.Candela.CANDELA\n" +
            "import org.pcb.unit.base.Gram.GRAM\n" +
            "import org.pcb.unit.base.Meter.METER\n" +
            "import org.pcb.unit.base.Numeric.NUMERIC\n" +
            "import org.pcb.unit.base.Second.SECOND\n" +
            "import org.pcb.unit.base.Kelvin.KELVIN\n" +
            "import org.pcb.unit.div\n" +
            "import org.pcb.unit.kilo\n" +
            "import org.pcb.unit.major\n" +
            "import org.pcb.unit.minor\n" +
            "import org.pcb.unit.times\n" +
            "import org.pcb.unit.toBigDecimal\n" +
            "\n" +
            "open class ${dimension.name}Amount${dimension.composite.buildGenericDeclaration()}(\n" +
            "\t\tamount: Number,\n" +
            "\t\tunit: ${dimension.composite.buildType()})\n" +
            "\t: Amount<${dimension.name}>(amount = amount.toBigDecimal(),\n" +
            "                           unit = CompositeUnit(\n" +
            "                               domain = ${dimension.name}::class,\n" +
            "                               units = mapOf(${dimension.composite.buildMap()})))\n" +
            "{\n" +
            dimension.composite.buildVariables() +
            dimension.composite.buildConstructor(dimension.name) +
            "\n" +
            "$operatorCode\n" +
            "}\n"

        val stub = this.javaClass.classLoader.getResource("package")!!

        val source = File(stub.path.replace("target/test-classes/package", "src/main/kotlin/org/pcb/unit/amounts/") + "${dimension.name}Amount.kt")

        source.writeText(contents.replace("\t", "    "))
    }
}