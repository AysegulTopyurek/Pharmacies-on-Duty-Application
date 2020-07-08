package com.example.haritaolay;

public class PharmacyModel {
    private String name;
    private String address;
    private String phone;
   // private String baslık;

    public PharmacyModel(String name, String address, String phone ) {
        this.name = name;
        this.address = address;
        this.phone = phone;

    }
    public PharmacyModel(String name, String address, String phone ,String baslık) {
        this.name = name;
        this.address = address;
        this.phone = phone;
       // this.baslık = baslık;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

 //   public String getBaslık() {
   //     return baslık;
    //}

    //public void setBaslık(String baslık) {
      //  this.baslık = baslık;
    //}

    //@Override
    public String toString() {
        return name + address + phone /* baslık*/;
    }
}
