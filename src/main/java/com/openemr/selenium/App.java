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
            client cl= new client(driver,"http://192.168.1.20/opendev/master","admin","pass");

            cl.login();
            
            tasks taskDriver = new tasks(cl);
            
            taskDriver.createPatient(new PatientData("firstName","lastName","1980-01-01","Female"));

        }
        catch(Exception e)
        {
            System.out.print(e.toString());
        }
    }
}
