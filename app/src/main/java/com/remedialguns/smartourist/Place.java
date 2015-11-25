package com.remedialguns.smartourist;


public class Place {
    public String type;
    private String name;
    private double distance;
    private double rate;
    private double cost;


    public  Place(String type, String name, double distance, double rate, double cost){
        this.type=type;
        this.name=name;
        this.distance=distance;
        this.rate=rate;
        this.cost=cost;
    }

    public String getName(){
        return name;
    }

    public double getDistance(){
        return distance;
    }

    public double getRate(){
        return rate;
    }

    public double getCost(){
        return cost;
    }

    public String getType(){
        return type;
    }

}
