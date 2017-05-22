package br.com.fiap.sub.burguerreview.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lsitec91.garcon on 15/05/2017.
 */

public class HamburgerHouse  {

    private String name;
    private String adress;
    private String strongPoint;
    private String weakPoint;
    private float noteSnack;
    private float noteSider;
    private float noteAmbient;
    private float priceRange;
    private String notes;

    public HamburgerHouse() {

    }

    public HamburgerHouse(String name, String adress, String strongPoint,
                          String weakPoint, int noteSnack, int noteSider,
                          int noteAmbient, int priceRange, String notes) {
        this.name = name;
        this.adress = adress;
        this.strongPoint = strongPoint;
        this.weakPoint = weakPoint;
        this.noteSnack = noteSnack;
        this.noteSider = noteSider;
        this.noteAmbient = noteAmbient;
        this.priceRange = priceRange;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStrongPoint() {
        return strongPoint;
    }

    public void setStrongPoint(String strongPoint) {
        this.strongPoint = strongPoint;
    }

    public String getWeakPoint() {
        return weakPoint;
    }

    public void setWeakPoint(String weakPoint) {
        this.weakPoint = weakPoint;
    }

    public float getNoteSnack() {
        return noteSnack;
    }

    public void setNoteSnack(float noteSnack) {
        this.noteSnack = noteSnack;
    }

    public float getNoteSider() {
        return noteSider;
    }

    public void setNoteSider(float noteSider) {
        this.noteSider = noteSider;
    }

    public float getNoteAmbient() {
        return noteAmbient;
    }

    public void setNoteAmbient(float noteAmbient) {
        this.noteAmbient = noteAmbient;
    }

    public float getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(float priceRange) {
        this.priceRange = priceRange;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
