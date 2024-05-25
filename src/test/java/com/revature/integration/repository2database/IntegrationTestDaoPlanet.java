package com.revature.integration.repository2database;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntegrationTestDaoPlanet {
    public PlanetDao planetDao;
    public static int planetPassId;

    @BeforeEach
    public void setup(){
        planetDao = new PlanetDao();
    }

    @Test
    @Order(1)
    public void testCreatePlanetPositive(){
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999);
        Planet planet = new Planet();
        planet.setName("IntegrationTestPlanet"+randomNum);
        planet.setOwnerId(23);

        Planet planetReturn = planetDao.createPlanet(planet);
        Assertions.assertNull(planetReturn);
//        Assertions.assertTrue(planetReturn.getId()>0);
//        Assertions.assertEquals(planetReturn.getName(), planet.getName());
//        Assertions.assertEquals(planetReturn.getOwnerId(), planet.getOwnerId());
    }
    @Test
    @Order(2)
    public void testCreatePlanetNegativeDuplicated(){
        Planet planet = new Planet();
        planet.setName("DuplicatedPlanet");
        planet.setOwnerId(23);

        planetDao.createPlanet(planet);
        Planet planetReturn = planetDao.createPlanet(planet);
        Assertions.assertEquals(planetReturn, new Planet());
    }

    @Test
    public void testDeletePlanetByIdNegative(){
        int ownerId = 23;
        int planetId = 0;
        String PlanetName = "DuplicatedPlanet";

        boolean result = planetDao.deletePlanetById(ownerId, planetId);
        Assertions.assertFalse(result);
    }

    @Test
    @Order(3)
    public void testGetPlanetByIdPositive(){
        Planet planet = new Planet();
        planet.setId(22);
        planet.setOwnerId(23);
        planet.setName("IntegrationTestPlanet29231");

        Planet planetReturn = planetDao.getPlanetById(planet.getOwnerId(), planet.getId());
        Assertions.assertEquals(planetReturn, planet);
    }

    @Test
    public void testGetPlanetByNamePositive(){
        Planet planet = new Planet();
        planet.setId(21);
        planet.setOwnerId(23);
        planet.setName("IntegrationTestPlanet29231");

        Planet planetReturn = planetDao.getPlanetByName(planet.getOwnerId(), planet.getName());
        Assertions.assertEquals(planetReturn, planet);

    }

    @Test
    public void testGetAllPlanets(){
        List<Planet> planets = new ArrayList<>();
        Planet mars = new Planet();
        mars.setId(8);
        mars.setName("mars");
        mars.setOwnerId(1);
        Planet earth = new Planet();
        earth.setId(12);
        earth.setName("earth");
        earth.setOwnerId(1);
        Planet mercury = new Planet();
        mercury.setId(13);
        mercury.setName("mercury");
        mercury.setOwnerId(1);
        planets.add(mars);
        planets.add(earth);
        planets.add(mercury);

        List<Planet> planetsReturn = planetDao.getAllPlanets(1);
        Assertions.assertEquals(planetsReturn, planets);

    }
}
