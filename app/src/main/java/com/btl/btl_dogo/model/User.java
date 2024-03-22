package com.btl.btl_dogo.model;

import java.io.Serializable;

public class User implements Serializable {
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    private String name;
        private String email;
        private String phone;
        private String address;
        private String password;

        // Constructors
        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String id,String name, String email, String phone, String address, String password) {
            this.Id=id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.password = password;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}
