package com.tennis.models.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tennis.models.dao.IActualizacionDao;
import com.tennis.models.dao.IJugadorDao;
import com.tennis.models.dao.IPartidoDao;
import com.tennis.models.dao.ITorneoDao;
import com.tennis.models.entity.Actualizacion;
import com.tennis.models.entity.Jugador;
import com.tennis.models.entity.Partido;
import com.tennis.models.entity.Torneo;
import com.tennis.models.entity.id.PartidoId;
import com.tennis.models.entity.id.TorneoId;
import com.tennis.models.service.IActualizarService;

@Service
public class ActualizarServiceImpl implements IActualizarService {

	@Autowired
	private Environment env;

	@Autowired
	private IJugadorDao jugadorDao;
	
	@Autowired
	private IActualizacionDao actualizacionDao;

	@Autowired
	private ITorneoDao torneoDao;

	@Autowired
	private IPartidoDao partidoDao;

	@Override
	public void cargarJugadores() {
		Reader in = null;
		Iterable<CSVRecord> records;
		try {
			URL url = new URL(env.getProperty("file.players"));
			URLConnection uc;
			uc = url.openConnection();

			in = new InputStreamReader(uc.getInputStream());
			CSVFormat format = CSVFormat.Builder.create(CSVFormat.RFC4180).setHeader().setSkipHeaderRecord(true)
					.build();

			CSVParser parser = CSVParser.parse(in, format);
			records = parser.getRecords();
			for (CSVRecord record : records) {
				String id = record.get("player_id");
				String nombre = record.get("name_first");
				String apellidos = record.get("name_last");
				// System.out.println(id + " - " + nombre + " - " + apellidos);
				if (!nombre.isEmpty() && !apellidos.isEmpty()) {
					try {
						Jugador jugador = new Jugador();
						jugador.setId(Long.parseLong(id));
						jugador.setNombre(nombre);
						jugador.setApellidos(apellidos);
						jugadorDao.save(jugador);
					} catch (Exception e) {
						System.out.println("Error al guardar jugador " + id + " - " + nombre + " - " + apellidos);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	@Async
	public void cargarPartidos(Actualizacion actualizacion) {
		//Se cargan los partidos en las tablas
		Reader in = null;
		try {
			URL url = new URL(env.getProperty("file.matches.challenger.2022"));
			URLConnection uc;
			uc = url.openConnection();

			in = new InputStreamReader(uc.getInputStream());
			CSVFormat format = CSVFormat.Builder.create(CSVFormat.RFC4180).setHeader().setSkipHeaderRecord(true)
					.build();

			CSVParser parser = CSVParser.parse(in, format);
			grabarDatosPartidos(parser.getRecords());
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		in = null;
		try {
			URL url = new URL(env.getProperty("file.matches.atp.2022"));
			URLConnection uc;
			uc = url.openConnection();

			in = new InputStreamReader(uc.getInputStream());
			CSVFormat format = CSVFormat.Builder.create(CSVFormat.RFC4180).setHeader().setSkipHeaderRecord(true)
					.build();

			CSVParser parser = CSVParser.parse(in, format);
			grabarDatosPartidos(parser.getRecords());
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		//Se ejecuta el PL para rellenar la tabla de estadisticas
		actualizacionDao.actualizarTablaEstadisticas();
		
		//Se actualiza la tabla de actualizaciones a finalizada
		actualizacion.setEstado(1);
		actualizacionDao.update(actualizacion);
	}

	private void grabarDatosPartidos(List<CSVRecord> records) {
		for (CSVRecord record : records) {
			try {
				String anioTorneo = record.get("tourney_id");
				Integer anio = Integer.parseInt(anioTorneo.substring(0, 4));
				Long idTorneo = Long.parseLong(anioTorneo.substring(5));
				String idPartido = record.get("match_num");
				String idGanador = record.get("winner_id");
				String idPerdedor = record.get("loser_id");
				String ronda = record.get("round");
				String resultado = record.get("score");

				// Si es la final se inserta el torneo
				if (ronda.equals("F")) {
					String nombreTorneo = record.get("tourney_name");
					String superficie = record.get("surface");
					String numJugadores = record.get("draw_size");
					String nivelTorneo = record.get("tourney_level");
					String fechaFinalTorneo = record.get("tourney_date");
					String sets = record.get("best_of");

					try {
						Torneo torneo = new Torneo();
						TorneoId torneoId = new TorneoId();
						torneoId.setAnio(anio);
						torneoId.setIdTorneo(idTorneo);
						torneo.setId(torneoId);
						torneo.setNombre(nombreTorneo);
						DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
						torneo.setFechaFinal(formatter.parse(fechaFinalTorneo));
						torneo.setNivel(nivelTorneo);
						torneo.setNumeroJugadores(Integer.parseInt(numJugadores));
						torneo.setSets(Integer.parseInt(sets));
						torneo.setSuperficie(superficie);
						torneoDao.save(torneo);
					} catch (Exception e) {
						System.out.println("Error al guardar torneo: " + idTorneo + " - " + anio);
					}
				}

				try {
					Partido partido = new Partido();
					PartidoId partidoId = new PartidoId();
					partidoId.setAnio(anio);
					partidoId.setIdTorneo(idTorneo);
					partidoId.setIdPartido(Integer.parseInt(idPartido));
					partido.setId(partidoId);
					partido.setIdGanador(Long.parseLong(idGanador));
					partido.setIdPerdedor(Long.parseLong(idPerdedor));
					partido.setResultado(resultado);
					partido.setRonda(ronda);
					partidoDao.save(partido);
				} catch (Exception e) {
					System.out.println("Error al guardar partido: " + idTorneo + " - " + anio);
				}
			} catch (Exception e) {
				System.out.println(
						"Error al obtener datos " + record.get("tourney_id") + " - " + record.get("match_num"));
			}
		}
		
	}

}
