fun main() {
    val prefix = "123"

    var result = prefix
    var operatorInd = getOperator(prefix)

    if (operatorInd != -1) {
        result = calculate(prefix, prefix[operatorInd].toString())
    }

    println(result)
}

fun getOperator(prefix: String): Int {
    if (prefix.isEmpty())
        return -1

    for (i in prefix.indices)
        if ("+-*/".contains(prefix[i]))
            return i

    return -1
}

fun calculate(prefix: String, operator: String): String {
    val operands = prefix.split(operator)

    if (operands.size != 2)
        return prefix

    val A: Double = operands[0].toDouble()
    val B: Double = operands[1].toDouble()

    return when (operator) {
        "+" -> {
            "${A + B}"
        }
        "-" -> {
            "${A - B}"
        }
        "*" -> {
            "${A * B}"
        }
        "/" -> {
            "${A / B}"
        }
        else -> {
            prefix
        }
    }
}
