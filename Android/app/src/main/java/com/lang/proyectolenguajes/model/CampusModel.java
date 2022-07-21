package com.lang.proyectolenguajes.model;

import com.lang.proyectolenguajes.data.CampusData;
import com.lang.proyectolenguajes.viewmodel.Campus;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CampusModel {
    private CampusData campusData;

    public CampusModel() {
        campusData = CampusData.getInstance();
    }

    public ArrayList<Campus> getCampuses() {
        return campusData.getCampusList();
    }
}
