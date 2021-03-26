package com.persona.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.persona.exception.DuplicateKeyException;
import com.persona.exception.InvalidFieldException;
import com.persona.business.PersonaBusiness;
import com.persona.entity.Persona;
import com.persona.response.BaseResponse;



/**
 * Toda clase Controller se encarga de:
 * 
 * 		1) Escuchar los mensajes provenientes del Cliente.
 * 
 * 		2) Enviar los mensajes provenientes el Cliente al Business y luego escuchar su respuesta.
 * 			NOTA: No hay que validar los mensajes, los mismos serán validados en el Business
 * 
 * 		3) Armar la respuesta para enviarle al Cliente que lo invocó.  		
 * 
 *  	4) Responderle al Cliente que lo invocó. 
 * 
 * */

@RestController  // Indica que esta clase es un Controller/Rest
@RequestMapping("/v1/personas") // v0: Versión de la API | personas: nombre del servicio en plural
public class PersonaController {
	
	@Autowired
	private PersonaBusiness personaBusiness;
	
	@GetMapping(value="")
	public List<Persona> listar() {
		
			return personaBusiness.listar();
		
	}
	
	@PostMapping(value="")
	public Persona crear(@RequestBody Persona persona) throws DuplicateKeyException, InvalidFieldException {
		return personaBusiness.crear(persona);
	}
	
	@DeleteMapping(value="/{id}")
	public void borrar(@PathVariable Integer id) throws NoSuchElementException, InvalidFieldException {
		
		personaBusiness.borrar(id);
	}

	@PutMapping(value="/{id}")
	public void modificar(@PathVariable Integer id, @RequestBody Persona persona) throws NoSuchElementException, InvalidFieldException {
		
		personaBusiness.modificar(id, persona);

	}
}
