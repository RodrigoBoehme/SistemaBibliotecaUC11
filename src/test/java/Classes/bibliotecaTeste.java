package Classes;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        biblioteca.adicionarLivro(livro1);
        // Verificar se livro foi adicionado à biblioteca

    }

    @Test
    @DisplayName("❌ Deve lançar exceção ao cadastrar livro nulo")
    void testCadastrarLivroNulo() {
        // TODO: Implementar teste para livro nulo
        assertThrows(IllegalArgumentException.class, ()->{
            biblioteca.adicionarLivro(null);
        },"A ");

        // Usar assertThrows para verificar exceção
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
    }

    @ParameterizedTest
    @DisplayName("💰 Deve calcular multa corretamente")
    @ValueSource(ints = {0, 5, 10}) // Dias de atraso
    void testCalcularMulta(int diasAtraso) {
        // TODO: Implementar teste parametrizado para cálculo de multa
        // Dica: usar LocalDate.now().plusDays(diasAtraso) para data de devolução
    }

    @Test
    @DisplayName("📚 Deve listar apenas livros disponíveis")
    void testListarLivrosDisponiveis() {
        // TODO: Implementar teste para listagem de livros disponíveis
        // Emprestar um livro e verificar se não aparece na lista
    }

    @Test
    @DisplayName("⏰ Deve bloquear empréstimo para usuário com multa")
    void testUsuarioComMulta() {
        // TODO: Implementar teste para usuário com multa
        // Configurar usuário com multa e tentar empréstimo
    }

    @AfterEach
    void tearDown() {
        // TODO: Limpar recursos se necessário
    }
}