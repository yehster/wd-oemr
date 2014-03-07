/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openemr.selenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.Alert;
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
        createPatient(data,false);
    }
    public void createPatient(PatientData data,boolean useExisting)
    {
        this.cl.menuClick("#new0");
        this.cl.switchToMain();
        this.cl.setField("#form_fname", data.get_fname());
        this.cl.setField("#form_lname", data.get_lname());
        this.cl.setField("#form_DOB", data.get_DOB());
        this.cl.setSelect("#form_sex", data.get_sex());
        if(data.get_mothers_name()!=null)
        {
            this.cl.setField("#form_usertext2", data.get_mothers_name());
        }
        this.cl.byCSS("#create").click();            


        this.cl.switchToPopup();
        boolean create=true;
        if(useExisting)
        {
            try{
                create=true;
                List<WebElement> searchResults=cl.wd.findElements(By.cssSelector("#searchResults tbody tr.oneresult"));
                boolean match;
                for(WebElement result: searchResults)
                {
                    String name=result.findElement(By.cssSelector("td.srName")).getText();
                    List<WebElement> srFields = result.findElements(By.cssSelector("td.srMisc"));
                    String sex=srFields.get(2).getText();
                    String dob=srFields.get(1).getText();
                    System.out.println(name+":"+dob+":"+sex);
                    match= name.equals(data.get_lname()+", "+data.get_fname()) && sex.equals(data.get_sex()) && dob.equals(data.get_DOB());
                    if(match)
                    {
                        result.click();
                        create=false;
                        break;
                    }
                }
                    
                
            }
            catch(Exception e)
            {
                System.out.println("Create!");
                create = true;
            }
        }
        if(create)
        {
            this.cl.byCSS("input[type='button'][value='Confirm Create New Patient']").click();        
        }
        this.cl.switchToTop();

    }
    
    public void newEncounter(String description, String category)
    {
        System.out.println("Starting Encounter");
        this.cl.menuClick("#nen1");
        this.cl.switchToEncounter();
        try{
            
                this.cl.byName("reason").sendKeys(description);
                this.cl.setSelectByContent("#pc_catid", category);
                this.cl.byCSS("a[href='javascript:saveClicked();']").click();
            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
//            this.cl.wd.switchTo().alert().dismiss();
            this.cl.switchToTop();
            this.cl.switchToEncounter();
            this.cl.byName("reason").sendKeys(description);
            this.cl.setSelectByContent("#pc_catid", category);
            this.cl.byCSS("a[href='javascript:saveClicked();']").click();            
        }
        

        
    }
    
}
