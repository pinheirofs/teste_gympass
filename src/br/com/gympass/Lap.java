package br.com.gympass;

public class Lap {

    private int number;
    private float avarage;
    private long time_ms;
    private long startTime;

    public Lap() {
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public float getAvarage() {
        return avarage;
    }

    public void setAvarage(final float avarage) {
        this.avarage = avarage;
    }

    public long getTime() {
        return time_ms;
    }

    public void setTime(final long time_ms) {
        this.time_ms = time_ms;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(final long startTime) {
        this.startTime = startTime;
    }
}
