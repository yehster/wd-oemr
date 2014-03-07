package com.openemr.selenium;

/**
 * Hello world!
 *
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Set;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        try
        {
            WebDriver driver =  remote.build("http://127.0.0.1:4444/wd/hub");
            //driver.initialize();
            client cl= new client(driver,"http://192.168.1.20/opendev/pcmedics","admin","pass");
//            client cl= new client(driver,"http://192.168.1.20/opendev/ippf","kyeh","kyeh");

            cl.login();
            
            tasks taskDriver = new tasks(cl);
           PatientData pat = new PatientData("Test","Male","1980-02-03","Male");

           taskDriver.createPatient(pat,true);
           taskDriver.newEncounter("Test Encounter", "Established Patient");
//            cl.menuClick("input[type='checkbox'][name='cb_bot']");


        }
        catch(Exception e)
        {
            System.out.print(e.toString());
        }
    }
}
