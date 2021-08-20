/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String userID;
    private String password;
    private String roleID;
    private String fullname;
    private String email;
    private String phone;
    private String photo;

    public UserDTO() {
    }

    public UserDTO(String userID, String password, String roleID, String fullname, String email, String phone, String photo) {
        this.userID = userID;
        this.password = password;
        this.roleID = roleID;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", password=" + password + ", roleID=" + roleID + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", photo=" + photo + '}';
    }
}
