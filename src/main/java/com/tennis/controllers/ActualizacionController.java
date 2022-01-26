package com.tennis.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tennis.models.dao.IActualizacionDao;
import com.tennis.models.entity.Actualizacion;
import com.tennis.models.service.IActualizarService;

@Controller
public class ActualizacionController {

	@Autowired
	private IActualizacionDao actualizacionDao;

	@Autowired
	private IActualizarService actualizarService;

	@RequestMapping(value = "/actualizacion/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Actualización de datos");
		model.addAttribute("actualizaciones", actualizacionDao.findAll());
		return "actualizacion/listar";
	}

	@RequestMapping(value = "/actualizacion/actualizar", method = RequestMethod.GET)
	public String actualizar(RedirectAttributes redirectAttrs) {
		// Comprobar si hay otra actualizacion en proceso
		if (actualizacionDao.actualizacionEnProceso()) {
			redirectAttrs.addFlashAttribute("mensajeError",
					"Existe otra actualizacion en proceso. Por favor, espere a que finalice.");
		} else {
			// Alta de actualizacion en tabla
			Actualizacion actualizacion = new Actualizacion();
			actualizacion.setFechaActualizacion(new Date());
			actualizacion.setEstado(0);
			Actualizacion act = actualizacionDao.save(actualizacion);
			// Llamada al service para actualizar
			actualizarService.cargarPartidos(act);
			redirectAttrs.addFlashAttribute("mensajeActualizando", "Se ha iniciado la actualización de los datos");
		}
		return "redirect:listar";
	}
}
