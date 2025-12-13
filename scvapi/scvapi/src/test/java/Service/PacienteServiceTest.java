package Service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    private PacienteService service;
    private Method validarMethod;

    @BeforeEach
    void setup() throws Exception {
        service = new PacienteService(null);

        validarMethod = PacienteService.class.getDeclaredMethod("validar", Paciente.class);
        validarMethod.setAccessible(true);
    }

    private void invokeValidar(Paciente p) {
        try {
            validarMethod.invoke(service, p);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException re) throw re;
            throw new RuntimeException(cause);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Paciente pacienteBaseValido() {
        Paciente p = new Paciente();
        p.setNome("a");
        p.setEmail("a@a.com");
        p.setCpf("1");
        return p;
    }

    @Test
    void deveLancarQuandoNomeForNull() {
        Paciente p = pacienteBaseValido();
        p.setNome(null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("Nome inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoNomeForVazio() {
        Paciente p = pacienteBaseValido();
        p.setNome("");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("Nome inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoNomeForApenasEspacos() {
        Paciente p = pacienteBaseValido();
        p.setNome(" ");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("Nome inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoNomeForMinimoValido() {
        Paciente p = pacienteBaseValido();
        p.setNome("a");

        assertDoesNotThrow(() -> invokeValidar(p));
    }

    @Test
    void deveLancarQuandoEmailForNull() {
        Paciente p = pacienteBaseValido();
        p.setEmail(null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoEmailForVazio() {
        Paciente p = pacienteBaseValido();
        p.setEmail("");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoEmailForApenasEspacos() {
        Paciente p = pacienteBaseValido();
        p.setEmail(" ");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoEmailForMinimoValido() {
        Paciente p = pacienteBaseValido();
        p.setEmail("a@a.com");

        assertDoesNotThrow(() -> invokeValidar(p));
    }

    @Test
    void deveLancarQuandoCpfForNull() {
        Paciente p = pacienteBaseValido();
        p.setCpf(null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("CPF inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoCpfForVazio() {
        Paciente p = pacienteBaseValido();
        p.setCpf("");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("CPF inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoCpfForApenasEspacos() {
        Paciente p = pacienteBaseValido();
        p.setCpf(" ");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(p));
        assertEquals("CPF inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoCpfForMinimoValido() {
        Paciente p = pacienteBaseValido();
        p.setCpf("12345678900");

        assertDoesNotThrow(() -> invokeValidar(p));
    }
}
