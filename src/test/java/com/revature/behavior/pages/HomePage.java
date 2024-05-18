package com.revature.behavior.pages;

import com.revature.models.Moon;
import com.revature.models.Planet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath="//select[@id='locationSelect']")
    Select dropdownMenu;
    @FindBy(xpath="//input[@id='planetNameInput']")
    WebElement addPlanet;
    @FindBy(xpath="//input[@id='moonNameInput']")
    WebElement addMoon;
    @FindBy(xpath="//input[@id='OrbitedPlanetInput']")
    WebElement addAssociatedPlanet;
    @FindBy(xpath="//input[@id='searchPlanetInput']")
    WebElement searchPlanet;
    @FindBy(xpath="//input[@id='searchMoonInput']")
    WebElement searchMoon;
    @FindBy(xpath="//table[@id='celestialTable']")
    WebElement celestialTable;

    @FindBy(xpath="//button[@class='submit-button']")
    WebElement submitButton;
    @FindBy(xpath="//button[@id='deleteButton']")
    WebElement deleteButton;
    @FindBy(xpath="//button[@id='searchPlanetButton']")
    WebElement searchPlanetButton;
    @FindBy(xpath="//button[@id='searchMoonButton']")
    WebElement searchMoonButton;

    public void navigateToPage(){
        driver.get("http://localhost:7000/api/webpage/home");
        driver.manage().window().maximize();
        // get the page title
        String title = driver.getTitle();
        System.out.println("The page title : "+ title);

    }
    public void setSelect(String option){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement sel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='locationSelect']")));
        dropdownMenu = new Select(sel);
        // value='moon' or 'planet'
        dropdownMenu.selectByValue(option);
    }
    public void addPlanet(String planet){
        addPlanet.sendKeys(planet);
        System.out.printf("add planet %s\n", planet);
    }
    public void addMoon(String moon, String orbitPlanet){
        addMoon.sendKeys(moon);
        addAssociatedPlanet.sendKeys(orbitPlanet);
        System.out.printf("add moon %s, with orbit planet %s\n", moon, orbitPlanet);
    }
    public void addSubmit(){
        submitButton.click();
    }
    public void deleteCelestial(){
        deleteButton.click();
    }

    public boolean foundPlanet(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement planetEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//tr[td[3]='%s']", name))));
        return planetEl != null;
    }
    public Planet getPlanet(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement planetEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//tr[td[3]='%s']", name))));
        return null;
    }

    public List<Planet> getAllPlanets(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> planetsEl = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//tr[td[1]='planet']")));

        List<Planet> planets = new ArrayList<>();
//        List<WebElement> planetsEl = celestialTable.findElements(By.xpath("//tr[td[1]='planet']"));
        for(WebElement e: planetsEl){
            Planet planet = new Planet();
            planet.setId(Integer.parseInt(e.getAttribute("tr[2]")));
            planets.add(planet);
            System.out.printf("planet id=%d, name=%s, owner=%d\n", planet.getId(), planet.getName(), planet.getOwnerId());
        }
        return planets;
    }
    public List<Moon> getAllMoons(){
        List<Moon> moons = new ArrayList<>();
        celestialTable.findElements(By.xpath("//tr/td[moon]"));
        return moons;
    }
    public Planet searchPlanet(String planetId){
        searchPlanet.sendKeys(planetId);
        searchPlanetButton.click();
        if(!getAllPlanets().isEmpty()) return getAllPlanets().get(0);
        return null;
    }
    public Planet searchMoon(String moonId){
        searchMoon.sendKeys(moonId);
        searchMoonButton.click();
        if(!getAllPlanets().isEmpty()) return getAllPlanets().get(0);
        return null;
    }

    public String addSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("home"));
        String title = driver.getTitle();
        System.out.println("Redirected to "+ title);
        return title;
    }
    public String removeSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("home"));
        String title = driver.getTitle();
        System.out.println("Redirected to "+ title);
        return title;
    }
    public String loginNotSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        alert.accept();
        return alertMessage;
    }
    public void closePage(){
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();

    }
}
