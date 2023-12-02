package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private boolean steeringWheelsHealth;
    private boolean breaksHealth;
    private boolean oilLiquidHealth;
    private boolean coolingLiquidHealth;
    private boolean airFiltersHealth;
    private boolean acHealth;
    private boolean cabinFilterHealth;
    private User user;
    private List<Check> checks = new ArrayList<>();
    private String brand;
    private String registerPlate;

    public String getRegisterPlate() {
        return registerPlate;
    }

    public void setRegisterPlate(String registerPlate) {
        this.registerPlate = registerPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isSteeringWheelsHealth() {
        return steeringWheelsHealth;
    }

    public void setSteeringWheelsHealth(boolean steeringWheelsHealth) {
        this.steeringWheelsHealth = steeringWheelsHealth;
    }

    public boolean isBreaksHealth() {
        return breaksHealth;
    }

    public void setBreaksHealth(boolean breaksHealth) {
        this.breaksHealth = breaksHealth;
    }

    public boolean isOilLiquidHealth() {
        return oilLiquidHealth;
    }

    public void setOilLiquidHealth(boolean oilLiquidHealth) {
        this.oilLiquidHealth = oilLiquidHealth;
    }

    public boolean isCoolingLiquidHealth() {
        return coolingLiquidHealth;
    }

    public void setCoolingLiquidHealth(boolean coolingLiquidHealth) {
        this.coolingLiquidHealth = coolingLiquidHealth;
    }

    public boolean isAirFiltersHealth() {
        return airFiltersHealth;
    }

    public void setAirFiltersHealth(boolean airFiltersHealth) {
        this.airFiltersHealth = airFiltersHealth;
    }

    public boolean isAcHealth() {return acHealth;}

    public void setAcHealth(boolean acHealth) {
        this.acHealth = acHealth;
    }

    public boolean isCabinFilterHealth() {
        return cabinFilterHealth;
    }

    public void setCabinFilterHealth(boolean cabinFilterHealth) {
        this.cabinFilterHealth = cabinFilterHealth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }
}
