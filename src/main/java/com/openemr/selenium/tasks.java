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
public class tasks {
    
    protected client cl;
    public tasks(client cl)
    {
        this.cl=cl;
    }
    
    public void createPatient(PatientData data)
    {
//                    driver.findElement(By.cssSelector("#form_fname")).sendKeys("FirstName");            
//            driver.findElement(By.cssSelector("#form_lname")).sendKeys("LastName");            
//            driver.findElement(By.cssSelector("#form_DOB")).sendKeys("01/01/1980");
//            driver.findElement(By.cssSelector("#form_sex > option[value='Female']")).click();
        this.cl.menuClick("#new0");
        this.cl.switchToMain();
        this.cl.setField("#form_fname", data.get_fname());
        this.cl.setField("#form_lname", data.get_lname());
        this.cl.setField("#form_DOB", data.get_DOB());
        this.cl.setSelect("#form_sex", data.get_sex());
        this.cl.byCSS("#create").click();            


        this.cl.switchToPopup();
        //this.cl.byCSS("input[type='button'][value='Confirm Create New Patient']").click();

    }
    
}
