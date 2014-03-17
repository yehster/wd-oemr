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
    
    public client cl;
    public tasks(client cl)
    {
        this.cl=cl;
    }
    
    public void createPatient(PatientData data)
    {
        createPatient(data,false);
    }
    
    public void populatePatientSearch(PatientData data)
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
        
    }
    public void createPatient(PatientData data,boolean useExisting)
    {
        populatePatientSearch(data);
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
                    // Match on other criteria needs tweaking for IPPF
                    match= name.equalsIgnoreCase(data.get_lname()+", "+data.get_fname()); // && sex.equals(data.get_sex()) && dob.equals(data.get_DOB());
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
        this.cl.switchToMain();
        this.cl.waitFor("span.title");
        // Add wait for Patient Info

    }
    
     public void newEncounter(String description, String category) throws Exception
    {
        newEncounter(description,category,true);
    }
    
    public void newEncounter(String description, String category, boolean createNew) throws Exception
    {
        System.out.println("Starting Encounter");
        this.cl.menuClick("#nen1");
        try{
            Alert al =this.cl.wd.switchTo().alert();
            System.out.println(al.getText());
            //Warning: A visit was already created for this patient today!
            //A visit already exists for this patient today. Click Cancel to open it, or OK to proceed with creating a new one.
            
            // Create New even if existing present
            if(createNew)
            {
                System.out.println("Ok Create New");
                al.accept();            
            }
            else
            {   
                System.out.println("Cancel. Open Existing");
                al.dismiss();
                return;
            }
        }
        catch(Exception e)
        {
            
        }
  
        this.cl.switchToEncounter();          
        this.cl.byName("reason").sendKeys(description);
        this.cl.setSelectByContent("#pc_catid", category);
        this.cl.byCSS("a[href='javascript:saveClicked();']").click();
                    

        
    }
    
    public void gotoFeeSheet()
    {
        this.cl.menuSection("Fees");
        this.cl.menuForm("fee_sheet");
    }
    
    public void feeSheetMultiCategory(String category)
    {
        try
        {
            Thread.sleep(2000);
            
        }
        catch(Exception e)
        {
            
        }
        String css="div.category-display > span";
        List<WebElement> categories = this.cl.elemsCSS(css);
        for(WebElement elem: categories)
        {
            System.out.println(elem.getText().trim());
            if(elem.getText().trim().equals(category))
            {
                elem.click();
            }
        }
    }
    

}
