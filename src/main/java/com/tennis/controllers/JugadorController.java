package com.tennis.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tennis.models.dao.IEstadisticasDao;
import com.tennis.models.dao.IJugadorDao;
import com.tennis.models.entity.Estadisticas;
import com.tennis.models.entity.Jugador;
import com.tennis.models.service.IActualizarService;

@Controller
public class JugadorController {
	
	//El qualifier no haría falta en este caso porque hay solo una implementacion de la interfaz
	//Se usaría para identificar que implementación usar si hubiera varias
	@Autowired
	@Qualifier("jugadorDaoImpl")
	private IJugadorDao jugadorDao;
	
	@Autowired
	private IEstadisticasDao estadisticasChallengerDao;
	
	@Autowired
	private IActualizarService actualizarService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		//actualizarService.cargarJugadores();
		//actualizarService.cargarPartidos();
		//List<EstadisticasChallenger> listaEstadisticas = estadisticasChallengerDao.findOrdenadosSuperficie("HARD");
		model.addAttribute("titulo", "Listado de jugadores");
		model.addAttribute("jugadores", jugadorDao.findAll());
		return "listar";
	}
	
	//Por defecto el metodo es get, así que no hace falta
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		model.put("titulo", "Formulario de jugador");
		return "form";
	}
	
	//@Valid es para que valide con las anotaciones del entity
	//El BindingResult es el resultado de la validacion
	//El titulo se tiene que meter como atributo igual que en el metodo de arriba
	//El cliente se pasa directamente siempre y cuando la clase se llame igual que el nombre del parametro
	//En este caso por lo tanto no haría falta, pero se haría con el @ModelAttribute
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("jugador") Jugador jugador, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de jugador");
			return "form";
		}
		jugadorDao.save(jugador);
		return "redirect:listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Jugador jugador = null;
		
		if (id > 0) {
			jugador = jugadorDao.findById(id);
		} else {
			return "redirect:listar";
		}
		model.put("jugador", jugador);
		model.put("titulo", "Editar cliente");
		return "form";
	}
}
