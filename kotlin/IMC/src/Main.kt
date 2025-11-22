import kotlin.math.pow

// validates
fun validarPeso(peso: Double?): Boolean =
    !(peso == null || peso < 0 || peso > 300)


fun validarAltura(altura: Double?): Boolean =
    !(altura == null || altura < 0 || altura > 3)

fun calcularIMC(peso: Double, altura: Double): Double =
    peso / altura.pow(2)

fun classificaIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc in 18.5..24.9 -> "Peso normal"
        imc in 25.0..29.9 -> "SobrePeso"
        imc in 30.0..34.9 -> "Obesidade Grau I"
        imc in 35.0..39.9 -> "Obesidade Grau II"
        imc > 39.0 -> "Obesidade Grau III"
        else -> "Desconhecida"
    }
}

enum class ClassificacaoIMC(val descricao: String) {
    ABAIXO_DO_PESO("Abaixo do Peso"),
    PESO_NORMAL("Peso Normal"),
    SOBREPESO("Sobrepeso"),
    OBESIDADE_GRAU_I("Obesidade Grau I"),
    OBESIDADE_GRAU_II("Obesidade Grau II"),
    OBESIDADE_GRAU_III("Obesidade Grau III"),
    DESCONHECIDO("Desconhecido");

    // Composição
    companion object {
        fun gerarClassificacao(imc: Double): ClassificacaoIMC {
            return when {
                imc < 18.5 -> ABAIXO_DO_PESO
                imc in 18.5..24.9 -> PESO_NORMAL
                imc in 25.0..29.9 -> SOBREPESO
                imc in 30.0..34.9 -> OBESIDADE_GRAU_I
                imc in 35.0..39.9 -> OBESIDADE_GRAU_II
                imc > 39.0 -> OBESIDADE_GRAU_III
                else -> DESCONHECIDO
            }
        }
    }

}



fun main() {

    println("Bem vindo ao Cálculo de IMC!")
    println("Informe o seu peso (em kg): ")
    print("-> ")


    val peso = readlnOrNull()?.toDoubleOrNull()

    if(!validarPeso(peso = peso)){
        println("O valor do peso inserido é inválido! ")
        return
    }

    println("Informe a sua altura: ")
    print("-> ")
    val altura = readlnOrNull()?.toDoubleOrNull()

    if(!validarAltura(altura = altura)){
        println("O valor da altura inserida é inválida! ")
        return
    }

    // !! -> diz que os valores não serão considerados nulos
    val imc = calcularIMC(peso = peso!!, altura = altura!!)

    val clIMC = ClassificacaoIMC.gerarClassificacao(imc = imc)

    println("O seu IMC é ${"%.1f".format(imc)} e sua classificação é '${clIMC.descricao}'")

}