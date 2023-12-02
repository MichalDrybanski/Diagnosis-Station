package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiagnosisStation {
    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void initDiagnosisCheck(Car cars, Map<String, String> todos) {
        Check check = new Check(todos);
        check.getDateTime();
        cars.getChecks().add(check);
    }

    public boolean checkCarState(Car car) {
        return (car.isSteeringWheelsHealth() && car.isBreaksHealth() && car.isOilLiquidHealth())
                && (car.isAirFiltersHealth() || car.isAcHealth() || car.isCabinFilterHealth());
    }
}
