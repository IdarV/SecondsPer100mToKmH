package idar.ninja.secondsp100mtokmh.stopwatch;
/*
Copyright (c) 2005, Corey Goldberg

StopWatch.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

Modified: Bilal Rabbani bilalrabbani1@live.com (Nov 2013)
*/

public class Stopwatch {
    private long startTime = 0;
    private boolean running = false;
    private long currentTime = 0;

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    public void pause() {
        this.running = false;
        currentTime = System.currentTimeMillis() - startTime;
    }
    public void resume() {
        this.running = true;
        this.startTime = System.currentTimeMillis() - currentTime;
    }

    //elaspsed time in milliseconds
    public long getElapsedTimeMili() {
        return running ? ((System.currentTimeMillis() - startTime)/100) % 1000 : 0;
    }

    public long getMili(){
        return running ? (System.currentTimeMillis() - startTime) % 1000 : 0;
    }

    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        return running ? ((System.currentTimeMillis() - startTime) / 1000) % 60 : 0;
    }

    //elaspsed time in minutes
    public long getElapsedTimeMin() {
        return running ? (((System.currentTimeMillis() - startTime) / 1000) / 60 ) % 60 : 0;
    }

    //elaspsed time in hours
    public long getElapsedTimeHour() {
        return running ? ((((System.currentTimeMillis() - startTime) / 1000) / 60 ) / 60) : 0;
    }
    public boolean isRunning(){
        return running;
    }

    public String formatNumber(long number){
        return (number > 10) ? "" + number : "0" + number;
    }

    public String toString() {
        return formatNumber(getElapsedTimeHour()) + ":" + formatNumber(getElapsedTimeMin()) + ":"
                + formatNumber(getElapsedTimeSecs()) + "." + formatNumber(getMili());
    }
}