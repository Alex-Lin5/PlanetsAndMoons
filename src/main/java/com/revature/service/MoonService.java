package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(Integer userId) {
		List<Moon> Moons = dao.getAllMoons(userId);
		return Moons;
	}

	public Moon getMoonByName(int userId, String moonName) {
		Moon foundMoon = dao.getMoonByName(moonName);
		List<Moon> moons = dao.getAllMoons(userId);
		if(moons.contains(foundMoon))
			return foundMoon;
		else return new Moon();
	}

	public Moon getMoonById(int userId, int moonId) {
		Moon foundMoon = dao.getMoonById(moonId);
		List<Moon> moons = dao.getAllMoons(userId);
		if(moons.contains(foundMoon))
			return foundMoon;
		else return new Moon();
	}

	public Moon createMoon(Moon m) {
		if(m.getName().length()>30 | m.getName().isBlank()) return new Moon();

		Moon createdMoon = dao.createMoon(m);
		if(createdMoon == null) return new Moon();
		if(createdMoon.getId() > 0){
			return createdMoon;
		}
		return new Moon();
	}

	public boolean deleteMoonById(int moonId) {
		return dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		List<Moon> Moons = dao.getMoonsFromPlanet(myPlanetId);
		return Moons;
	}
}
