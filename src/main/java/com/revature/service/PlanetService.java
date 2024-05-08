package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets() {
		List<Planet> Planets = dao.getAllPlanets();
		return Planets;
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		Planet foundPlanet = dao.getPlanetByName(planetName);
		if(foundPlanet.getOwnerId() == ownerId){
			return foundPlanet;
		}
		return null;
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		Planet foundPlanet = dao.getPlanetById(planetId);
		if(foundPlanet.getOwnerId() == ownerId){
			return foundPlanet;
		}
		return null;
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		if(planet.getName().length()>30 | planet.getName().isBlank()) return new Planet();

		planet.setOwnerId(ownerId);
		Planet createdPlanet = dao.createPlanet(planet);
		if(createdPlanet == null){
			return new Planet();
		}
//		if(createdPlanet.getId() == 0){
//			return createdPlanet; // try to create existed planet, conflict
//		}
//		if(createdPlanet.getOwnerId() == ownerId){
//			return createdPlanet;
//		}
		return createdPlanet;
	}

	public boolean deletePlanetById(int ownerId, int planetId) {
		return dao.deletePlanetById(planetId);
	}
}
