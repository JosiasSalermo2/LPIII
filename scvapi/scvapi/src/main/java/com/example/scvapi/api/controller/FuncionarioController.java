package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.EstoqueDTO;
import com.example.scvapi.api.dto.FuncionarioDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Endereco;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.model.entity.Telefone;
import com.example.scvapi.service.CargoService;
import com.example.scvapi.service.EnderecoService;
import com.example.scvapi.service.FuncionarioService;
import com.example.scvapi.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
@CrossOrigin
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    private final CargoService cargoService;
    private final TelefoneService telefoneService;
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity get(){
        List<Funcionario> funcionarios = funcionarioService.getFuncionario();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody FuncionarioDTO dto) {
        try {
            Funcionario funcionario = converter(dto);
            funcionario = funcionarioService.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FuncionarioDTO dto) {
        if (!funcionarioService.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = converter(dto);
            funcionario.setId(id);
            funcionario = funcionarioService.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try{
            funcionarioService.excluir(funcionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Funcionario converter(FuncionarioDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);

        if(dto.getCargoId() != null){
            cargoService.getCargoById(dto.getCargoId()).ifPresent(funcionario::setCargo);
        }

        if (dto.getTelefoneId() != null){
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getTelefoneId());
            if (!telefone.isPresent()) {
                funcionario.setTelefone(null);
            }else{
                funcionario.setTelefone(telefone.get());
            }
        }

        if (dto.getEnderecoId() != null){
            Optional<Endereco> endereco = enderecoService.getEnderecoById(dto.getEnderecoId());
            if (!endereco.isPresent()) {
                funcionario.setEndereco(null);
            }else{
                funcionario.setEndereco(endereco.get());
            }
        }
        return funcionario;
    }
}
