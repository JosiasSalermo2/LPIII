import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.repository.FuncionarioRepository;
import com.example.scvapi.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceMockTest {

    @Mock
    private FuncionarioRepository repository;

    @InjectMocks
    private FuncionarioService service;

    private Funcionario funcionarioValido() {
        Funcionario f = new Funcionario();
        f.setNome("a");
        f.setEmail("a@a.com");
        f.setCpf("11122233344");
        return f;
    }

    @Test
    void deveSalvarQuandoFuncionarioForValido() {
        Funcionario f = funcionarioValido();

        when(repository.save(f)).thenReturn(f);

        Funcionario retorno = service.salvar(f);

        assertNotNull(retorno);
        verify(repository, times(1)).save(f);
    }
    
    @Test
    void naoDeveSalvarQuandoNomeForNull() {
        Funcionario f = funcionarioValido();
        f.setNome(null);

        RegraNegocioException ex =
                assertThrows(RegraNegocioException.class, () -> service.salvar(f));

        assertEquals("Nome inválido", ex.getMessage());
        verify(repository, never()).save(any());
    }
}
