/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openemr.selenium;

/**
 *
 * @author yehster
 */
public class PatientData {
    protected String fname,lname,DOB,sex,mothers_name;
    public PatientData(String fname,String lname,String DOB,String sex)
    {
        this.fname=fname;
        this.lname=lname;
        this.DOB=DOB;
        this.sex=sex;
    }
    
    public String get_fname()
    {
        return this.fname;
    }

    public String get_lname()
    {
        return this.lname;
    }
    
    public String get_DOB()
    {
        System.out.println(this.DOB);
        return this.DOB;
    }    

    public String get_sex()
    {
        return this.sex;
    }
    
    public void set_mothers_name(String val)
    {
        this.mothers_name=val;
    }
    
    public String get_mothers_name()
    {
        return this.mothers_name;
    }
}
