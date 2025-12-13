package Service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceValidarTest {

    private FuncionarioService service;
    private Method validarMethod;

    @BeforeEach
    void setup() throws Exception {
        service = new FuncionarioService(null);

        validarMethod = FuncionarioService.class.getDeclaredMethod("validar", Funcionario.class);
        validarMethod.setAccessible(true);
    }

    private void invokeValidar(Funcionario funcionario) {
        try {
            validarMethod.invoke(service, funcionario);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException re) throw re;
            throw new RuntimeException(cause);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Funcionario funcionarioBaseValida() {
        Funcionario f = new Funcionario();
        f.setNome("a");
        f.setEmail("a@a.com");
        f.setCpf("123");
        return f;
    }

    @Test
    void deveLancarQuandoNomeForNull() {
        Funcionario f = funcionarioBaseValida();
        f.setNome(null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("Nome inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoNomeForVazio() {
        Funcionario f = funcionarioBaseValida();
        f.setNome("");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("Nome inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoNomeForApenasEspacos() {
        Funcionario f = funcionarioBaseValida();
        f.setNome(" ");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("Nome inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoNomeForMinimoValido() {
        Funcionario f = funcionarioBaseValida();
        f.setNome("a");

        assertDoesNotThrow(() -> invokeValidar(f));
    }


    @Test
    void deveLancarQuandoEmailForNull() {
        Funcionario f = funcionarioBaseValida();
        f.setEmail(null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoEmailForVazio() {
        Funcionario f = funcionarioBaseValida();
        f.setEmail("");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoEmailForApenasEspacos() {
        Funcionario f = funcionarioBaseValida();
        f.setEmail("   ");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoEmailForMinimoValido() {
        Funcionario f = funcionarioBaseValida();
        f.setEmail("a@a.com");

        assertDoesNotThrow(() -> invokeValidar(f));
    }

    @Test
    void deveLancarQuandoCpfForNull() {
        Funcionario f = funcionarioBaseValida();
        f.setCpf(null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("CPF inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoCpfForVazio() {
        Funcionario f = funcionarioBaseValida();
        f.setCpf("");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("CPF inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoCpfForApenasEspacos() {
        Funcionario f = funcionarioBaseValida();
        f.setCpf(" ");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(f));
        assertEquals("CPF inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoCpfForMinimoValido() {
        Funcionario f = funcionarioBaseValida();
        f.setCpf("11122233344");

        assertDoesNotThrow(() -> invokeValidar(f));
    }
}
