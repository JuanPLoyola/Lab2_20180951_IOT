package com.example.lab2_2018951.data;

import com.example.lab2_2018951.model.Router;
import com.example.lab2_2018951.model.SwitchDevice;
import com.example.lab2_2018951.model.AccessPoint;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static DataStore INSTANCE;

    public static DataStore getInstance() {
        if (INSTANCE == null) INSTANCE = new DataStore();
        return INSTANCE;
    }

    private final List<Router> routers = new ArrayList<>();
    private final List<SwitchDevice> switches = new ArrayList<>();
    private final List<AccessPoint> accessPoints = new ArrayList<>();

    // Routers
    public List<Router> getRouters() { return routers; }
    public void addRouter(Router r){ routers.add(r); }
    public void replaceRouter(Router oldR, Router newR){
        int idx = routers.indexOf(oldR);
        if (idx >= 0) routers.set(idx, newR);
    }

    // Switches
    public List<SwitchDevice> getSwitches() { return switches; }
    public void addSwitch(SwitchDevice s){ switches.add(s); }
    public void replaceSwitch(SwitchDevice oldS, SwitchDevice newS){
        int idx = switches.indexOf(oldS);
        if (idx >= 0) switches.set(idx, newS);
    }

    // Access Points
    public List<AccessPoint> getAccessPoints() { return accessPoints; }
    public void addAp(AccessPoint ap){ accessPoints.add(ap); }
    public void replaceAp(AccessPoint oldAp, AccessPoint newAp){
        int idx = accessPoints.indexOf(oldAp);
        if (idx >= 0) accessPoints.set(idx, newAp);
    }
}
