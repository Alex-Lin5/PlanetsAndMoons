package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;
	private PlanetService pdao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
//		pdao =
	}

	public List<Moon> getAllMoons() {
//		List<Planet> userPlanets = pdao.getAllPlanets();
		List<Moon> Moons = dao.getAllMoons();
		return Moons;
	}

	public Moon getMoonByName(int myPlanetId, String moonName) {
		Moon foundMoon = dao.getMoonByName(moonName);
		if(foundMoon.getMyPlanetId() == myPlanetId){
			return foundMoon;
		}
		return null;
	}

	public Moon getMoonById(int myPlanetId, int moonId) {
		Moon foundMoon = dao.getMoonById(moonId);
		if(foundMoon.getMyPlanetId() == myPlanetId){
			return foundMoon;
		}
		return null;
	}

	public Moon createMoon(Moon m) {
		if(m.getName().length()>30 | m.getName().isBlank()) return new Moon();

		Moon createdMoon = dao.createMoon(m);
		if(createdMoon == null) return new Moon();
		if(createdMoon.getId() > 0){
			return createdMoon;
		}
		return null;
	}

	public boolean deleteMoonById(int moonId) {
		return dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		List<Moon> Moons = dao.getMoonsFromPlanet(myPlanetId);
		return Moons;
	}
}
