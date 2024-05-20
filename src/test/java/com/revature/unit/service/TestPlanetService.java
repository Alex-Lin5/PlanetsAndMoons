package com.revature.unit.service;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;
import com.revature.service.PlanetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class TestPlanetService {
    @Mock
    PlanetDao planetDao;
    @InjectMocks
    PlanetService planetService;

    @Test
    public void testCreatePlanetPositive(){
//        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        Planet planetInput = new Planet();
        planetInput.setOwnerId(23);
        planetInput.setName("planetTest");
        Planet planetReturn = new Planet();
        planetReturn.setId(1);
        planetReturn.setOwnerId(23);
        planetReturn.setName("planetTest");
        Mockito.when(planetDao.createPlanet(planetInput)).thenReturn(planetReturn);
        Planet planetActual = planetService.createPlanet(planetInput);
        Mockito.verify(planetDao, Mockito.times(1)).createPlanet(planetInput);
        Assertions.assertEquals(planetActual, planetReturn);
    }
    @Test
    public void testCreatePlanetNegative(){
//        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        Planet planetInput = new Planet();
        planetInput.setOwnerId(23);
        planetInput.setName("planetTestDup");
        Mockito.when(planetDao.createPlanet(planetInput)).thenReturn(null);
        Planet planetActual = planetService.createPlanet(planetInput);
        Mockito.verify(planetDao, Mockito.times(1)).createPlanet(planetInput);
        Assertions.assertEquals(planetActual, new Planet());
    }
    @Test
    public void testDeletePlanetByIdPositive(){
        int planetId = 20;
        int ownerId = 23;
        Mockito.when(planetDao.deletePlanetById(ownerId, planetId)).thenReturn(true);
        boolean result = planetService.deletePlanetById(ownerId, planetId);
        Mockito.verify(planetDao, Mockito.times(1)).deletePlanetById(ownerId, planetId);
        Assertions.assertTrue(result);
    }
    @Test
    public void testDeletePlanetByIdNegative(){
        int planetId = 2;
        int ownerId = 23;
        Mockito.when(planetDao.deletePlanetById(ownerId, planetId)).thenReturn(false);
        boolean result = planetService.deletePlanetById(ownerId, planetId);
        Mockito.verify(planetDao, Mockito.times(1)).deletePlanetById(ownerId, planetId);
        Assertions.assertFalse(result);
    }
    @Test
    public void testGetAllPlanetsPositive(){
        int ownerId = 23;
        List<Planet> planets = new ArrayList<>();
        Planet planet = new Planet();
        planet.setId(5);
        planet.setName("testGetAllPlanetsPositive");
        planet.setOwnerId(ownerId);
        planets.add(planet);

        Mockito.when(planetDao.getAllPlanets(ownerId)).thenReturn(planets);
        List<Planet> planetsActual = planetService.getAllPlanets(ownerId);
        Mockito.verify(planetDao, Mockito.times(1)).getAllPlanets(ownerId);
        Assertions.assertEquals(planetsActual, planets);

    }
    @Test
    public void testGetPlanetByNamePositive(){
        int ownerId = 23;
        String planetName = "testGetPlanetByNamePositive";
        Planet planet = new Planet();
        planet.setId(5);
        planet.setName("testGetPlanetByNamePositive");
        planet.setOwnerId(ownerId);

        Mockito.when(planetDao.getPlanetByName(ownerId, planetName)).thenReturn(planet);
        Planet planetActual = planetService.getPlanetByName(ownerId, planetName);
        Mockito.verify(planetDao, Mockito.times(1)).getPlanetByName(ownerId, planetName);
        Assertions.assertEquals(planetActual, planet);

    }

    @Test
    public void testGetPlanetByIdPositive(){
        int ownerId = 23;
        int planetId = 5;
        Planet planet = new Planet();
        planet.setId(5);
        planet.setName("testGetPlanetByIdPositive");
        planet.setOwnerId(ownerId);

        Mockito.when(planetDao.getPlanetById(ownerId, planetId)).thenReturn(planet);
        Planet planetActual = planetService.getPlanetById(ownerId, planetId);
        Mockito.verify(planetDao, Mockito.times(1)).getPlanetById(ownerId, planetId);
        Assertions.assertEquals(planetActual, planet);

    }

}
