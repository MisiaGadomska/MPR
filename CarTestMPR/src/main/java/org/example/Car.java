package org.example;

    public class Car {
    private int battery;    //stan baterii
    private int fuelAmount;     //jak duzo paliwa ma samochod
    private int fuelCapacity;     //ile max moze miec paliwa
    private float fuelConsumption;      //ile zuzywa na km
    private float distanceTravelled = 0;    //ile przejechal

    public Car(int battery, int fuelAmount, int fuelCapacity, float fuelConsumption){
        this.battery = battery;
        this.fuelAmount = fuelAmount;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
    }

        public float getDistanceTravelled() {
            return distanceTravelled;
        }

        public void setDistanceTravelled(float distanceTravelled) {
            this.distanceTravelled = distanceTravelled;
        }

        public boolean turnOn(){
        return this.battery > 10 && this.fuelAmount > 20;
    }
    public boolean drive(float kilometers){
        float spentFuel = kilometers * fuelConsumption;
        if (spentFuel <= fuelAmount) {
            fuelAmount -= (int) spentFuel;
            distanceTravelled += kilometers;
            return true;
        }
        return false;
    }

    public boolean refuel(int fuelAmount){
        if(fuelAmount <= 0){
            return false;
        }
        int newValue = this.fuelAmount + fuelAmount;
        this.fuelAmount = Math.min(newValue, fuelCapacity); // wybiera mniejsza wartosc z dwoch
        return true;
    }

        public int getBattery() {
            return battery;
        }

        public void setBattery(int battery) {
            this.battery = battery;
        }

        public int getFuelAmount() {
            return fuelAmount;
        }

        public void setFuelAmount(int fuelAmount) {
            this.fuelAmount = fuelAmount;
        }

        public int getFuelCapacity() {
            return fuelCapacity;
        }

        public void setFuelCapacity(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
        }

        public float getFuelConsumption() {
            return fuelConsumption;
        }

        public void setFuelConsumption(float fuelConsumption) {
            this.fuelConsumption = fuelConsumption;
        }
    }
