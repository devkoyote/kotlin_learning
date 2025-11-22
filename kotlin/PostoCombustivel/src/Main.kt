//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    println("Insira o preço por litro da Gasolina: ")
    print("R$ ")
    val priceGasolina = readlnOrNull()?.toDoubleOrNull()

    println("Insira o preço por litro do Etanol: ")
    print("R$ ")
    val priceEtanol = readlnOrNull()?.toDoubleOrNull()


    if (priceGasolina == null || priceEtanol == null) {
        println("Os valores não foram inseridos!")
        return
    }

    val razCombustivel = priceEtanol / priceGasolina

    println (
        when {
            razCombustivel < 0.7 -> "Etanol é mais econômico! "
            razCombustivel > 0.7 -> "Gasolina é mais econômica! "
            else -> "Ambos possuem o mesmo beneficio! "
        }
    )

}