package Service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.service.VacinacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacinacaoServiceValidarTest {

    private VacinacaoService service;
    private Method validarMethod;

    @BeforeEach
    void setup() throws Exception {
        service = new VacinacaoService(null);

        validarMethod = VacinacaoService.class.getDeclaredMethod("validar", Vacinacao.class);
        validarMethod.setAccessible(true);
    }

    private void invokeValidar(Vacinacao vacinacao) {
        try {
            validarMethod.invoke(service, vacinacao);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException re) throw re;
            throw new RuntimeException(cause);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Vacinacao vacinacaoComPaciente(String nome, String email, String dataNascimento, String dataAplicacao) {
        return vacinacaoComPaciente(nome, email, dataNascimento, dataAplicacao, null);
    }

    private Vacinacao vacinacaoComPaciente(String nome, String email, String dataNascimento, String dataAplicacao, Agendamento agendamento) {
        Vacinacao v = mock(Vacinacao.class);
        Paciente p = mock(Paciente.class);

        when(v.getPaciente()).thenReturn(p);
        when(p.getNome()).thenReturn(nome);
        when(p.getEmail()).thenReturn(email);
        when(p.getDataNascimento()).thenReturn(dataNascimento);

        when(v.getDataAplicacao()).thenReturn(dataAplicacao);
        when(v.getAgendamento()).thenReturn(agendamento);

        return v;
    }

    @Test
    void deveLancarQuandoVacinacaoForNull() {
        RegraNegocioException ex =
                assertThrows(RegraNegocioException.class, () -> invokeValidar(null));
        assertEquals("Vacinacao inválida", ex.getMessage());
    }

    @Test
    void deveLancarQuandoPacienteForNull() {
        Vacinacao v = mock(Vacinacao.class);
        when(v.getPaciente()).thenReturn(null);

        RegraNegocioException ex =
                assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Paciente inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoNomePacienteForNull() {
        Vacinacao v = vacinacaoComPaciente(null, null, null, null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Paciente inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoNomePacienteForVazio() {
        Vacinacao v = vacinacaoComPaciente("", null, null, null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Paciente inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoEmailPacienteForNull() {
        Vacinacao v = vacinacaoComPaciente("a", null, null, null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Email inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoEmailPacienteForVazio() {
        Vacinacao v = vacinacaoComPaciente("a", "", null, null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Email inválido", ex.getMessage());
    }

    @Test
    void deveLancarQuandoDataNascimentoForNull() {
        Vacinacao v = vacinacaoComPaciente("a", "a@a.com", null, null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Data de nascimento inválida", ex.getMessage());
    }

    @Test
    void deveLancarQuandoDataNascimentoForVazia() {
        Vacinacao v = vacinacaoComPaciente("a", "a@a.com", "", null);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Data de nascimento inválida", ex.getMessage());
    }

    @Test
    void deveLancarQuandoDataAplicacaoForNull() {
        Vacinacao v = vacinacaoComPaciente("a", "a@a.com", "2000-01-01", null);

        RegraNegocioException ex =
                assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Data de aplicação inválida", ex.getMessage());
    }

    @Test
    void deveLancarQuandoDataAplicacaoForVazia() {
        Vacinacao v = vacinacaoComPaciente("a", "a@a.com", "2000-01-01", "");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Data de aplicação inválida", ex.getMessage());
    }

    @Test
    void deveLancarQuandoAgendamentoForNull() {
        Vacinacao v = vacinacaoComPaciente("a", "a@a.com", "2000-01-01", "2025-01-01T10:00");

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> invokeValidar(v));
        assertEquals("Agendamento inválido", ex.getMessage());
    }

    @Test
    void naoDeveLancarQuandoDadosValidos() {
        Agendamento ag = mock(Agendamento.class);

        Vacinacao v = vacinacaoComPaciente(
                "a", "a@a.com", "2000-01-01", "2025-01-01T10:00", ag
        );

        assertDoesNotThrow(() -> invokeValidar(v));
    }

}