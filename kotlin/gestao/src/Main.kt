/**
 * Data Class que representa um modelo de Produto
 * @property id Identificador único do produto
 * @property nome Nome do produto
 * @property preco Preço unitário do produto
 * @property quantidade Quantidade em estoque
 */
data class Produtos(
    val id: Int,
    val nome: String,
    val preco: Double,
    val quantidade: Int
)

/**
 * Interface que define as operações básicas de um estoque
 * @param T Tipo genérico para os itens do estoque
 */
interface Estoque<T> {
    fun inserir(item: T)
    fun deletar(id: Int): Boolean
    fun atualizar(item: T): Boolean
    fun buscar(id: Int): T?
    fun buscarTodos(): List<T>
}

/**
 * Implementação concreta do estoque para produtos
 * Utiliza padrão DAO (Data Access Object)
 */
class EstoqueDeProdutos() : Estoque<Produtos> {

    // Lista mutável para armazenar e manipular os produtos
    private val listaProdutos = mutableListOf<Produtos>()

    /**
     * Insere um novo produto no estoque
     * @param item Produto a ser inserido
     */
    override fun inserir(item: Produtos) {
        listaProdutos.add(item)
    }

    /**
     * Remove um produto do estoque pelo ID
     * @param id Identificador do produto a ser removido
     * @return true se o produto foi removido, false caso contrário
     */
    override fun deletar(id: Int): Boolean {
        return listaProdutos.removeIf { it.id == id }
    }

    /**
     * Atualiza um produto existente no estoque
     * @param item Produto com os dados atualizados
     * @return true se a atualização foi bem-sucedida, false caso contrário
     */
    override fun atualizar(item: Produtos): Boolean {
        // Remove o produto antigo e adiciona o novo
        if (listaProdutos.removeIf { it.id == item.id }) {
            listaProdutos.add(item)
            return true
        } else {
            return false
        }
    }

    /**
     * Busca um produto específico pelo ID
     * @param id Identificador do produto
     * @return Produto encontrado ou null se não existir
     */
    override fun buscar(id: Int): Produtos? {
        return listaProdutos.find { it.id == id }
    }

    /**
     * Retorna todos os produtos do estoque
     * @return Lista com todos os produtos
     */
    override fun buscarTodos(): List<Produtos> {
        return listaProdutos.toList()
    }
}

/**
 * Função auxiliar para capturar dados do usuário e criar um novo produto
 * @return Novo objeto Produto com os dados informados
 */
fun preencherProduto(): Produtos {
    var id: Int? = null
    while (id == null) {
        println("Insira o ID do produto:")
        print("-> ")
        id = readlnOrNull()?.toIntOrNull()
        if (id == null) {
            println("O ID inserido é inválido. Tente novamente!")
        }
    }

    var nome: String? = null
    while (nome == null) {
        println("Insira o nome do produto:")
        print("-> ")
        nome = readlnOrNull()
        if (nome == null) {
            println("O nome inserido é inválido. Tente novamente!")
        }
    }

    var preco: Double? = null
    while (preco == null) {
        println("Insira o preço do produto:")
        print("->R$: ")
        preco = readlnOrNull()?.toDoubleOrNull()
        if (preco == null) {
            println("O preço inserido é inválido. Tente novamente!")
        }
    }

    var quantidade: Int? = null
    while (quantidade == null) {
        println("Insira a quantidade de produtos em estoque:")
        print("-> ")
        quantidade = readlnOrNull()?.toIntOrNull()
        if (quantidade == null) {
            println("A quantidade inserida é inválida. Tente novamente!")
        }
    }

    return Produtos(
        id = id,
        nome = nome,
        preco = preco,
        quantidade = quantidade
    )
}

/**
 * Função auxiliar para capturar dados atualizados de um produto
 * @param idProdutoAtualizado ID do produto a ser atualizado
 * @return Produto atualizado com os novos dados
 */
fun preencherProdutoAtualizado(idProdutoAtualizado: Int): Produtos {
    var id: Int? = null
    while (id == null) {
        println("Insira o ID do produto:")
        print("-> ")
        id = readlnOrNull()?.toIntOrNull()
        if (id == null) {
            println("O ID inserido é inválido. Tente novamente!")
        }
    }

    var nome: String? = null
    while (nome == null) {
        println("Insira o nome do produto:")
        print("-> ")
        nome = readlnOrNull()
        if (nome == null) {
            println("O nome inserido é inválido. Tente novamente!")
        }
    }

    var preco: Double? = null
    while (preco == null) {
        println("Insira o preço do produto:")
        print("->R$: ")
        preco = readlnOrNull()?.toDoubleOrNull()
        if (preco == null) {
            println("O preço inserido é inválido. Tente novamente!")
        }
    }

    var quantidade: Int? = null
    while (quantidade == null) {
        println("Insira a quantidade de produtos em estoque:")
        print("-> ")
        quantidade = readlnOrNull()?.toIntOrNull()
        if (quantidade == null) {
            println("A quantidade inserida é inválida. Tente novamente!")
        }
    }

    return Produtos(
        id = id,
        nome = nome,
        preco = preco,
        quantidade = quantidade
    )
}

/**
 * Função principal que executa o sistema de gerenciamento de estoque
 * Apresenta um menu interativo para o usuário
 */
fun main() {
    // Inicializa o estoque de produtos
    val estoqueDeProdutos = EstoqueDeProdutos()

    // Variável de controle para o menu principal
    var acao: Int? = null

    // Loop principal do sistema
    while (acao != 5) {
        // Exibe o menu de opções
        println(
            """
                =====================================
                          SISTEMA DE PRODUTOS
                =====================================
                
                  1 - Adicionar
                  2 - Deletar
                  3 - Buscar
                  4 - Atualizar
                  5 - Sair
                  
                =====================================
            """.trimIndent()
        )

        // Exibe a lista atual de produtos
        println("LISTA ATUAL DE PRODUTOS EM ESTOQUE: ")
        println(
            estoqueDeProdutos.buscarTodos().joinToString(separator = "\n")
                .ifEmpty { "Nenhum produto foi adicionado ainda no estoque!" }
        )
        print("-> ")

        // Captura a ação do usuário
        acao = readlnOrNull()?.toIntOrNull()

        // Processa a ação selecionada
        when (acao) {
            1 -> {
                // Adicionar novo produto
                val produto = preencherProduto()
                estoqueDeProdutos.inserir(item = produto)
                println("Produto inserido com sucesso!")
            }
            2 -> {
                // Deletar produto existente
                var idDeletar: Int? = null
                println("Insira o ID do produto a ser deletado: ")

                while (idDeletar == null || idDeletar < 0) {
                    print("-> ")
                    idDeletar = readlnOrNull()?.toIntOrNull()
                    if (idDeletar == null || idDeletar < 0) {
                        println("O ID inserido é inválido. Tente novamente!")
                    }
                }

                val produtoDeletado = estoqueDeProdutos.deletar(idDeletar)
                println(
                    if (produtoDeletado) "O produto foi deletado com sucesso!"
                    else "Não existe produto com esse ID!"
                )
            }
            3 -> {
                // Buscar produto por ID
                var idProduto: Int? = null
                println("Insira um ID de produto a ser buscado:")

                while (idProduto == null || idProduto < 0) {
                    print(" -> ")
                    idProduto = readlnOrNull()?.toIntOrNull()
                    if (idProduto == null || idProduto < 0) {
                        println("O ID inserido é inválido!")
                    }
                }

                val produtoBuscado = estoqueDeProdutos.buscar(idProduto)
                println(
                    if (produtoBuscado != null) "O produto $produtoBuscado"
                    else "Nenhum produto com esse ID!!!"
                )
            }
            4 -> {
                // Atualizar produto existente
                var id: Int? = null
                println("Insira o ID do produto a ser buscado: ")

                while (id == null || id < 0) {
                    id = readlnOrNull()?.toIntOrNull()
                    if (id == null || id < 0) {
                        println("O ID inserido é inválido. Tente novamente!")
                    }
                }

                val produtoBuscado = estoqueDeProdutos.buscar(id = id)
                println(
                    if (produtoBuscado != null) "O produto buscado é: $produtoBuscado"
                    else "Não existe nenhum produto com esse ID"
                )
            }
            5 -> {
                // Sair do sistema
                println("Sistema fechado com segurança, até logo!!!")
            }
            else -> {
                // Opção inválida
                println("Não existe a opção no sistema!!!")
            }
        }
    }
}