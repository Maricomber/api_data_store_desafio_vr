package com.api.data.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;
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

import com.api.data.dto.PessoaDTO;
import com.api.data.response.Response;
import com.api.data.services.PessoaService;

import io.swagger.annotations.*;

@RestController
@RequestMapping(path = {"/api/pessoa"})
public class PessoaController {
	
	@Autowired
	PessoaService service;
	
	@ApiOperation(value = "Retorna uma lista de pessoas")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista de pessoas retornada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<List<PessoaDTO>>> findAll(HttpServletRequest request) {
		
		Response<List<PessoaDTO>> response = new Response<>();
		List<String>erros = new ArrayList<>();
		
		try{
			List<PessoaDTO>pessoaDTO = this.service.findAll();
			
			if(pessoaDTO.isEmpty()) {
				throw new InvalidAttributesException("Registro de pessoas não encontrado");
			}
			response.setData(pessoaDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Retorna uma pessoa por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Pessoa pesquisada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<PessoaDTO>> findById(@PathVariable Integer id){
		
		List<String>erros = new ArrayList<>();
		Response<PessoaDTO>response = new Response<>();
		PessoaDTO pessoaDTO;
		
		try {
			
			if(id == null) {
				throw new InvalidAttributesException("Campos em branco");
			}
			
			pessoaDTO= this.service.findById(id);
			
			if(pessoaDTO == null) {
				throw new InvalidAttributesException("Usuario não encontrado. ");
			}
			response.setData(pessoaDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Cria um registro de pessoa")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Registro de pessoa criado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<Response<PessoaDTO>> save(@RequestBody PessoaDTO pessoaDTO) {
		
		Response<PessoaDTO> response = new Response<>();
		List<String>erros = new ArrayList<>();
		
		try {

			if(pessoaDTO == null) {
				throw new InvalidAttributesException("Campos vazios. ");
			}
			pessoaDTO = this.service.save(pessoaDTO);
			response.setData(pessoaDTO);
			return ResponseEntity.ok(response);
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Atualiza um registro de pessoa")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Pessoa atualizado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<PessoaDTO>> update(@RequestBody PessoaDTO pessoaDTO){
		
		List<String>erros = new ArrayList<>();
		Response<PessoaDTO>response = new Response<>();
		
		try {
			pessoaDTO = this.service.save(pessoaDTO);
			if(pessoaDTO == null) {
				return ResponseEntity.badRequest().body(response);
			}
		response.setData(pessoaDTO);
		return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation(value = "Deleta um registro de pessoa")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Pessoa deletada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Response<String>> delete(@PathVariable Integer id) {
		
		Response<String> response = new Response<>();
		List<String>erros = new ArrayList<>();
		
		try {
			if(id == null) {
				throw new InvalidAttributesException("Campos em branco. ");
			}
			this.service.delete(id);
			response.setData("Deletado com sucesso");
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
