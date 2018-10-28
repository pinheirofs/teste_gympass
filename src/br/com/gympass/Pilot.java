package br.com.gympass;

import java.util.HashMap;
import java.util.Map;

public class Pilot {

    private String name;
    private int code;
    private final Map<Integer, Lap> laps = new HashMap<>();

    public Pilot() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void put(final Lap lap) {
        laps.put(lap.getNumber(), lap);
    }

    public Map<Integer, Lap> getLaps() {
        return new HashMap<>(laps);
    }

}