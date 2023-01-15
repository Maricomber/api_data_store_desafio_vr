package com.api.data.controllers;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.data.dto.ContaDTO;
import com.api.data.response.Response;
import com.api.data.services.ContaServices;

import io.swagger.annotations.*;

@RestController
@RequestMapping(path = {"/api/conta"})
public class ContaController {
	
	@Autowired
	ContaServices service;
	
	@ApiOperation(value = "Retorna uma lista de contas")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista de contas retornada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<List<ContaDTO>>> findContas(HttpServletRequest request) {
		
		Response<List<ContaDTO>> response = new Response<>();
		List<String>erros = new ArrayList<>();
		
		try{
			List<ContaDTO>contaDTO = this.service.findAll();
			
			if(contaDTO.isEmpty()) {
				throw new SQLDataException("Registro de contas não encontrado");
			}
			response.setData(contaDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Retorna uma conta por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Conta pesquisada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<ContaDTO>> findById(@PathVariable Integer id){
		
		List<String>erros = new ArrayList<>();
		Response<ContaDTO>response = new Response<>();
		ContaDTO contaDTO;
		
		try {
			
			contaDTO= this.service.findById(id);
			
			if(contaDTO == null) {
				throw new SQLDataException("Conta não encontrado. ");
			}
			response.setData(contaDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Cria um registro de conta")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Registro de conta criado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<Response<List<ContaDTO>>> save(@RequestBody List<ContaDTO> contaDTO) {
		
		Response<List<ContaDTO>> response = new Response<>();
		List<String>erros = new ArrayList<>();
		
		try {
			
			contaDTO = this.service.save(contaDTO);
			if(contaDTO.isEmpty()) {
				throw new SQLDataException("Erro ao salvar conta");
			}
			response.setData(contaDTO);
			return ResponseEntity.ok(response);
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Atualiza um registro de conta")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Conta atualizado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<List<ContaDTO>>> update(@RequestBody List<ContaDTO> contaDTO){
		
		List<String>erros = new ArrayList<>();
		Response<List<ContaDTO>>response = new Response<>();
		
		try {
			contaDTO = this.service.save(contaDTO);
			if(contaDTO.isEmpty()) {
				throw new SQLDataException("Erro ao salvar conta");
			}
		response.setData(contaDTO);
		return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation(value = "Deleta um registro de conta")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Conta deletada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Response<String>> delete(@PathVariable Integer id) {
		
		Response<String> response = new Response<>();
		List<String>erros = new ArrayList<>();
		
		try {
			this.service.delete(id);
			response.setData("Conta deletada com sucesso");
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
