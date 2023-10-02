/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author WitherDragon
 */
public class NhanVienSession {
    private NhanVien loggedInNhanVien;
    private boolean isLoggedIn;

    public NhanVienSession() {
        this.loggedInNhanVien = null;
        this.isLoggedIn = false;
    }

    public void setLoggedInNhanVien(NhanVien loggedInNhanVien) {
        this.loggedInNhanVien = loggedInNhanVien;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void logout() {
        loggedInNhanVien = null;
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public NhanVien getLoggedInNhanVien() {
        return loggedInNhanVien;
    }

}
