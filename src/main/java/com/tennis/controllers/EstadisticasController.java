package com.tennis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tennis.models.dao.IEstadisticasDao;
import com.tennis.models.entity.Estadisticas;
import com.tennis.utils.Constantes;

@Controller
public class EstadisticasController {
	
	@Autowired
	private IEstadisticasDao estadisticasDao;
	
	
	@RequestMapping(value="/estadisticasChallenger/dura", method=RequestMethod.GET)
	public String estadisticasChallengerDura(Model model) {
		List<Estadisticas> listaEstadisticasDura = estadisticasDao.findOrdenadosSuperficie(Constantes.SUPERFICIE_DURA, Constantes.NIVEL_CHALLENGER);
		model.addAttribute("titulo", "Estadísticas últimos torneos");
		model.addAttribute("estadisticasDura", listaEstadisticasDura);
		return "estadisticasChallenger/dura";
	}
	
	@RequestMapping(value="/estadisticasChallenger/hierba", method=RequestMethod.GET)
	public String estadisticasChallengerHierba(Model model) {
		List<Estadisticas> listaEstadisticasHierba = estadisticasDao.findOrdenadosSuperficie(Constantes.SUPERFICIE_HIERBA, Constantes.NIVEL_CHALLENGER);
		model.addAttribute("titulo", "Estadísticas últimos torneos");
		model.addAttribute("estadisticasHierba", listaEstadisticasHierba);
		return "estadisticasChallenger/hierba";
	}
	
	@RequestMapping(value="/estadisticasChallenger/tierra", method=RequestMethod.GET)
	public String estadisticasChallengerTierra(Model model) {
		List<Estadisticas> listaEstadisticasTierra = estadisticasDao.findOrdenadosSuperficie(Constantes.SUPERFICIE_TIERRA, Constantes.NIVEL_CHALLENGER);
		model.addAttribute("titulo", "Estadísticas últimos torneos");
		model.addAttribute("estadisticasTierra", listaEstadisticasTierra);
		return "estadisticasChallenger/tierra";
	}
	
	@RequestMapping(value="/estadisticasAtp/dura", method=RequestMethod.GET)
	public String estadisticasAtpDura(Model model) {
		List<Estadisticas> listaEstadisticasDura = estadisticasDao.findOrdenadosSuperficie(Constantes.SUPERFICIE_DURA, Constantes.NIVEL_ATP);
		model.addAttribute("titulo", "Estadísticas últimos torneos");
		model.addAttribute("estadisticasDura", listaEstadisticasDura);
		return "estadisticasAtp/dura";
	}
	
	@RequestMapping(value="/estadisticasAtp/hierba", method=RequestMethod.GET)
	public String estadisticasAtpHierba(Model model) {
		List<Estadisticas> listaEstadisticasHierba = estadisticasDao.findOrdenadosSuperficie(Constantes.SUPERFICIE_HIERBA, Constantes.NIVEL_ATP);
		model.addAttribute("titulo", "Estadísticas últimos torneos");
		model.addAttribute("estadisticasHierba", listaEstadisticasHierba);
		return "estadisticasAtp/hierba";
	}
	
	@RequestMapping(value="/estadisticasAtp/tierra", method=RequestMethod.GET)
	public String estadisticasAtpTierra(Model model) {
		List<Estadisticas> listaEstadisticasTierra = estadisticasDao.findOrdenadosSuperficie(Constantes.SUPERFICIE_TIERRA, Constantes.NIVEL_ATP);
		model.addAttribute("titulo", "Estadísticas últimos torneos");
		model.addAttribute("estadisticasTierra", listaEstadisticasTierra);
		return "estadisticasAtp/tierra";
	}
	
}
