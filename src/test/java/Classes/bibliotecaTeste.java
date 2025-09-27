package Classes;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTeste {
    private Biblioteca biblioteca;
    private Livro livro1;
    private Livro livro2;
    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();

        livro1 = new Livro("123456", "Dom Casmurro", "Machado de Assis", 1899);
        livro2 = new Livro("789012", "O Cortiço", "Aluísio Azevedo", 1890);


        usuario1 = new Usuario("U001", "João Silva", "joao@email.com");
        usuario2 = new Usuario("U002", "Maria Santos", "maria@email.com");

        // TODO: Adicionar livros e usuários à biblioteca
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);
    }

    @Test
    @DisplayName("✅ Deve cadastrar livro com sucesso")
    void testCadastrarLivro() {
        // TODO: Implementar teste para cadastro de livro

        Livro livroTeste=new Livro("001987","Finafi","Scott Catota",1987);
        biblioteca.adicionarLivro(livroTeste);
        Livro livro01=biblioteca.buscarLivroPorIsbn("001987");
        assertEquals(livro01,livroTeste,"Livro adicionado deveria aparecer ao pesquisa-lo");
        // Verificar se livro foi adicionado à biblioteca

    }

    @Test
    @DisplayName("❌ Deve lançar exceção ao cadastrar livro nulo")
    void testCadastrarLivroNulo() {
        // TODO: Implementar teste para livro nulo
        // Usar assertThrows para verificar exceção

        assertThrows(IllegalArgumentException.class, ()->{
            biblioteca.adicionarLivro(null);
        },"A ");

    }

    @Test
    @DisplayName("✅ Deve realizar empréstimo com sucesso")
    void testEmprestarLivro() {
        // TODO: Implementar teste de empréstimo bem-sucedido
        // Verificar se livro ficou indisponível
        biblioteca.emprestarLivro("123456","U001",LocalDate.now());
        assertFalse(biblioteca.buscarLivroPorIsbn("123456").isDisponivel(),"Livro Nao ficou indisponivel");

    }

    @Test
    @DisplayName("❌ Não deve emprestar livro indisponível")
    void testEmprestarLivroIndisponivel() {
        // TODO: Implementar teste para livro já emprestado
        biblioteca.emprestarLivro("123456","U001",LocalDate.now());
        assertFalse(biblioteca.emprestarLivro("123456","U002",LocalDate.now()),"Tentar Emprestar um Livro não disponivel deveria retornar false");


    }

    @ParameterizedTest
    @DisplayName("💰 Deve calcular multa corretamente")
    @ValueSource(ints = {0, 5, 10}) // Dias de atraso
    void testCalcularMulta(int diasAtraso) {
        // TODO: Implementar teste parametrizado para cálculo de multa
        // Dica: usar LocalDate.now().plusDays(diasAtraso) para data de devolução

        biblioteca.emprestarLivro("123456","U001",LocalDate.now().plusDays(-14));

        assertEquals(biblioteca.devolverLivro("123456",LocalDate.now().plusDays(diasAtraso)),diasAtraso*2);



    }

    @Test
    @DisplayName("📚 Deve listar apenas livros disponíveis")
    void testListarLivrosDisponiveis() {
        // TODO: Implementar teste para listagem de livros disponíveis
        // Emprestar um livro e verificar se não aparece na lista
        biblioteca.emprestarLivro("123456","U001",LocalDate.now());
        List<Livro> lista1=biblioteca.listarLivrosDisponiveis();
        assertFalse(lista1.contains(livro1),"Lista não deveria conter livro1");

    }

    @Test
    @DisplayName("⏰ Deve bloquear empréstimo para usuário com multa")
    void testUsuarioComMulta() {
        // TODO: Implementar teste para usuário com multa
        // Configurar usuário com multa e tentar empréstimo
        usuario1.setPossuiMulta(true);
        assertFalse(biblioteca.emprestarLivro("123456","U001",LocalDate.now()),"Esta tentativa de emprestimo deveria retornar false");
    }

    @AfterEach
    void tearDown() {
        // TODO: Limpar recursos se necessário
        usuario1.setPossuiMulta(false);
        usuario2.setPossuiMulta(false);
        livro1.setDisponivel(true);
        livro2.setDisponivel(true);

    }
}