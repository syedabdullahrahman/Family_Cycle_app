package com.example.user.familycyclefinal.objects;

public class currentUser {
    private static currentUser instance = null;
    private Kazi kazi;
    private MarriedCouple mc;
    protected currentUser() {
        // Exists only to defeat instantiation.
    }

    public currentUser(Kazi kazi, MarriedCouple mc) {
        this.kazi = kazi;
        this.mc = mc;
    }

    public Kazi getKazi() {
        return kazi;
    }

    public void setKazi(Kazi kazi) {
        this.kazi = kazi;
    }

    public MarriedCouple getMc() {
        return mc;
    }

    public void setMc(MarriedCouple mc) {
        this.mc = mc;
    }

    public static void setInstance(currentUser instance) {
        currentUser.instance = instance;
    }

    public static currentUser getInstance() {
        if (instance == null) {
            instance = new currentUser();
        }
        return instance;
    }
}