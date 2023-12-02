package org.example.controller;

import org.example.model.*;
import org.example.view.View;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * Connector between <code>View</code> and <code>Model</code> classes.
 * Controller takes the data from <code>View</code> and then passes it to
 * <code>Model</code>. Once the results are calculated Controller passes
 * back the data to <code>View</code>.
 */
public class Controller {
    private View view;
    //TODO: please add appropriate model dependencies
    private DiagnosisStation diagnosisStation;
    private User users;

    public Controller() {
        view = new View();
        diagnosisStation = new DiagnosisStation();
        users = new User();
    }

    public void control() {
        String operation;
        while (true) {
            //TODO: implement the main app loop
            operation = view.getString("Choose one of operation u want to do: \n" +
                    "1. Insert new car and user\n" +
                    "2. Data about cars\n" +
                    "3. Data about car owners\n" +
                    "4. Diagnosis check history\n" +
                    "5. Check action\n" +
                    "6. Modify car\n" +
                    "7. Change status of choosen check\n" +
                    "------------------------------\n" +
                    "Exit - if u want to leave.\n");
            if ("Exit".equals(operation)) break;
            switch (operation) {
                case "1" -> {
                    diagnosisStation.getCars().add(initCar());
                }
                case "2" -> {
                    for (Car car : diagnosisStation.getCars())
                        view.printMessage(carAsString(car));
                }
                case "3" -> {
                    printDateUser(view.getString("Insert register plate your car: "));
                }
                case "4" -> {
                    // perform check action
                    // ask if car was checked in the diagnosis station before
                    // get info if car is checked as Yes/No
                    Car carToBeChecked = null;
                    // if yes ("Yes".equals(choice)), then ask for User data and use EXISTING Car from the list from diagnosis station
                    if ("Yes".equals(view.getString("Is car checked?: Yes/No"))) {
                        String plate = view.getString("Insert register plate: ");
                        for (Car it : diagnosisStation.getCars())
                            if (it.getRegisterPlate().equals(plate))
                                carToBeChecked = it;
                        // if not ("No".equals(choice)), then ask for (full) Car data and then add Car to the list from diagnosis station (in fact performing add Car action)
                    } else {
                        carToBeChecked = initCar();
                        diagnosisStation.getCars().add(carToBeChecked);
                    }

                    //TODO: please provide the Map of to-do list for diagnosisCheck
                    // example: Map.of("Task 1", "check oil", "Task 2", "check cooling liquid")

                    diagnosisStation.initDiagnosisCheck(carToBeChecked, Map.of("Task 1: ", "Check steering wheels", "Task 2: ", "Check breaks", "Task 3: ", " Check oil liquid",
                            "Task 4: ", "Check cooling liquid", "Task 5: ", "Check air filters", "Task 6: ", "Check ac", "Task 7: ", "Check cabin filter"));
                    boolean carState = diagnosisStation.checkCarState(carToBeChecked);
                    //TODO: complete all the tasks under the task to-do list, but only and only if the car state is ok,
                    // otherwise mark appropriate tasks as failed or mark all of them as failed (it's up to u bro!) :)
                    if (carState) {
                        //TODO: find the last check (with the smallest date)\
                        Check currentCheck = null ;  //TODO: method is not working as expect(o patronum)ed :( TOO OPTIMISTC :)

                        LocalDateTime dateTime = LocalDateTime.now();
                        long diffMin = carToBeChecked.getChecks().get(0).getDateTime().until(dateTime, ChronoUnit.DAYS);
                        Check lastCheck = carToBeChecked.getChecks().get(0);
                        for (Check check : carToBeChecked.getChecks()) {
                            long localMin = check.getDateTime().until(dateTime, ChronoUnit.DAYS);
                            if (localMin < diffMin) { // podopowiedz by kjuba :)
                                diffMin = localMin;
                                lastCheck = check;
                            }
                        }
                        // TODO: complete all the tasks
                        for (Activity activity : lastCheck.getToDoList()) {
                            if(activity.getStatus() == Activity.Status.SUCCEEDED){

                            }else {
                                while (activity.getStatus() != Activity.Status.SUCCEEDED) {
                                    activity.changeStatus();
                                }
                            }
                        }
                        view.printMessage("All tasks " + (lastCheck.isTodoListCompleted() ? "are completed" : " are not completed"));
                    }
                    // having User&Car given, perform check method from diagnosis station (take care to add the Check to the Car's checks history)
                    // perform check on the carToBeChecked
                }
                case "5" -> {
                    printChecksHistory(view.getString("Insert Name: "), view.getString("\nInsert Surname: "));
                }
                case "6" -> {
                    if ("Yes".equals(view.getString("Do you want to modife car?: Yes/No"))) {
                        String plate = view.getString("Insert register plate your car: ");
                        for (Car it : diagnosisStation.getCars())
                            if (it.getRegisterPlate().equals(plate)) {
                                diagnosisStation.getCars().remove(it);
                                diagnosisStation.getCars().add(initCar());
                            }
                    }
                }
                case "7" ->{
                    changeStatusOfActivity(view.getInt("Choose Id of check: "));
                }
                default -> throw new UnsupportedOperationException("Please choose correct operation from 1-4. ");
            }
        }
    }
    // zmienic stan statusu dla wybranego badania- checku
    public Check changeStatusOfActivity(Integer checkId){
        // find check:
        // -> iterate over all Cars
        Check resultCheck = null;
        for(Car car : diagnosisStation.getCars()){
            for(Check check : car.getChecks()){
                if(check.getId().equals(checkId)){
                    for(Activity activity : check.getToDoList()){
                        view.printMessage("For Activity: " + activity + " the status has been changed: "
                                + activity.changeStatus(view.getInt("Insert status value: ")));
                        resultCheck = check;
                    }
                }
            }
        }
        // -> for each Cars iterate over all of the checks that Car
        // -> every time check if given Check id is equal to checkId param

        // once check is found then iterate over all Activities in the to-do list
        // for the given check
        // give the possibility to change the status of each Activities

        return resultCheck;
    }

    //  private boolean performCheck(Car car){
    //      if(diagnosisStation.performDiagnosisCheck(car))
    // }

    private void printDateUser(String registerPlate) {
        for (Car it : diagnosisStation.getCars()) {
            if (it.getRegisterPlate().equals(registerPlate)) {
                view.printMessage("Register plate: " + it.getRegisterPlate() + "\nName: " + it.getUser().getName() + "\nSurname: " + it.getUser().getSurname());
            }
        }
    }

    private void printChecksHistory(String name, String surname) {
        for (Car it : diagnosisStation.getCars())
            if (it.getUser().getName().equals(name) && it.getUser().getSurname().equals(surname))
                // print all checks for car it as the owner of
                // car it has the same name&surname as given one
                for (Check check : it.getChecks()) {
                    view.printMessage("Check history: " + check);
                }

    }

    private boolean yesNoToBoolean(String yesNo) {
        return "yes".equals(yesNo);
    }

    private String trueFalseToString(boolean trueFalse) {
        return trueFalse ? "yes" : "no";
    }

    private Car initCar() {
        Car car = new Car();
        car.setBrand(view.getString("Insert the car brand: "));
        car.setSteeringWheelsHealth(yesNoToBoolean(view.getString("Insert the car steeringWheelsHealth: ")));
        car.setBreaksHealth(yesNoToBoolean(view.getString("Insert the car breaksHealth: ")));
        car.setOilLiquidHealth(yesNoToBoolean(view.getString("Insert the car oilLiquidHealth: ")));
        car.setCoolingLiquidHealth(yesNoToBoolean(view.getString("Insert the car coolingLiquidHealth")));
        car.setAirFiltersHealth(yesNoToBoolean(view.getString("Insert the car airFiltersHealth")));
        car.setAcHealth(yesNoToBoolean(view.getString("Insert the car acHealth")));
        car.setCabinFilterHealth(yesNoToBoolean(view.getString("Insert the car CabinFilterHealth")));
        car.setRegisterPlate(view.getString("Insert the register plate"));
        car.setUser(initUser());
        return car;
    }

    private User initUser() {
        User user = new User();
        user.setName(view.getString("Insert user name: "));
        user.setSurname(view.getString("Insert user surname: "));
        return user;
    }

    private String carAsString(Car car) {
        String printableCar = "";
        printableCar += car.getBrand() + " ";
        printableCar += trueFalseToString((car.isSteeringWheelsHealth())) + " ";
        printableCar += trueFalseToString(car.isBreaksHealth()) + " ";
        printableCar += trueFalseToString(car.isOilLiquidHealth()) + " ";
        printableCar += trueFalseToString(car.isCoolingLiquidHealth()) + " ";
        printableCar += trueFalseToString(car.isAirFiltersHealth()) + " ";
        printableCar += trueFalseToString(car.isAcHealth()) + " ";
        printableCar += trueFalseToString(car.isCabinFilterHealth()) + " ";
        printableCar += trueFalseToString(car.isCabinFilterHealth()) + " ";
        printableCar += car.getRegisterPlate() + " ";
        return printableCar;
    }

}
