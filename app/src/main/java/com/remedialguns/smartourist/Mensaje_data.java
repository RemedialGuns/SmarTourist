package com.remedialguns.smartourist;

/**
 * Created by santiago on 04/12/2015.
 */
import java.io.Serializable;

public class Mensaje_data implements Serializable{

    private static final long serialVersionUID = 9178463713495654837L;

    public String type;
    private String name;
    private double distance;
    private double rate;
    private double cost;

}