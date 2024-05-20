package com.revature.integration.repository2database;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class IntegrationTestMoon {
    public MoonDao moonDao;
    
    @BeforeEach
    public void setup(){
        moonDao = new MoonDao();
    }
    @Test
    public void testCreateMoonPositive(){
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        Moon moon = new Moon();
        moon.setName("IntegrationTestMoon"+randomNum);
        moon.setMyPlanetId(23);

        Moon moonReturn = moonDao.createMoon(moon);
        Assertions.assertNull(moonReturn);
    }
    @Test
    public void testCreateMoonNegative(){
        Moon moon = new Moon();
        moon.setName("DuplicatedMoon");
        moon.setMyPlanetId(23);

        moonDao.createMoon(moon);
        Moon moonReturn = moonDao.createMoon(moon);
        Assertions.assertEquals(moonReturn, new Moon());
    }

    @Test
    public void testDeleteMoonByIdNegative(){
        Moon moon = new Moon();
        moon.setId(0);
        moon.setMyPlanetId(23);

        boolean result = moonDao.deleteMoonById(moon.getId());
        Assertions.assertFalse(result);
    }

    @Test
    public void testGetMoonByIdPositive(){
        Moon moon = new Moon();
        moon.setId(14);
        moon.setMyPlanetId(23);
        moon.setName("IntegrationTestMoon6900");

        Moon moonReturn = moonDao.getMoonById(moon.getId());
        Assertions.assertEquals(moonReturn, moon);
    }

    @Test
    public void testGetMoonByNamePositive(){
        Moon moon = new Moon();
        moon.setId(14);
        moon.setMyPlanetId(23);
        moon.setName("IntegrationTestMoon6900");

        Moon moonReturn = moonDao.getMoonByName(moon.getName());
        Assertions.assertEquals(moonReturn, moon);
    }

    @Test
    public void testGetAllMoons(){

    }

}
