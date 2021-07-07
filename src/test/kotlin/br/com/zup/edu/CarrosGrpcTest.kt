package br.com.zup.edu
import br.com.zup.edu.carros.Carro
import br.com.zup.edu.carros.CarroRepository
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(rollback = false , transactionMode = TransactionMode.SINGLE_TRANSACTION,transactional = false)
class CarrosGrpcTest {
    /**
     * Estrategias:
     * louca: sujou,limpou -> @AfterEach
     * louca: limpou,usou -> @BeforeEach[x]
     * louca: usa louca descartavel -> roolback= true
     * louca:uso a louca,jogo fora,compro nova -> recria o banco a cada teste
     * **/

    @Inject
    lateinit var repository: CarroRepository

    @BeforeEach
    internal fun setUp() {
        repository.deleteAll()
    }

    @AfterEach
    internal fun tearDown() {
        repository.deleteAll()

    }

    @Test
    fun `deve inserir um novo carro`() {
    //cenario
        repository.deleteAll()
    //acao
        repository.save(Carro("Gol","HPX-1234"))
        //validacao
      assertEquals(1,repository.count())

    }//commit
    @Test
    fun `deve encontrar carro por placa`(){
        //cenario

     //   repository.deleteAll()
        repository.save(Carro("Palio","OIP-9876"))
        //acao
        val encontrado =repository.existsByPlaca("OIP-9876")

        //validacao
        assertTrue(encontrado)

    }


}
