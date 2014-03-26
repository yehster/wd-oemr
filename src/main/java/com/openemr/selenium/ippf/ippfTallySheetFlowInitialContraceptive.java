/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openemr.selenium.ippf;

import com.openemr.selenium.PatientData;
import com.openemr.selenium.client;
import com.openemr.selenium.remote;
import com.openemr.selenium.tasks;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author yehster
 */
public class ippfTallySheetFlowInitialContraceptive {
    public static void main( String[] args )
    {
        System.out.println( "Tally Sheet Flow Start" );

        try
        {
            WebDriver driver =  remote.build("http://127.0.0.1:4444/wd/hub");
            //driver.initialize();
//            client cl= new client(driver,"http://192.168.1.20/opendev/pcmedics","admin","pass");
            client cl= new client(driver,"http://192.168.1.20/opendev/ippf","kyeh","kyeh");

            cl.login();
            
            tasks taskDriver = new tasks(cl);
            
           PatientData pat = new PatientData("Tally","Test","1980-02-03","Female");
           pat.set_mothers_name("MothersName");
           taskDriver.createPatient(pat,false);
           
           taskDriver.enterEncounter("Tally Sheet Contraception", "2 Re-Visit / Re-Supply");
           taskDriver.cl.activateEncounterFrame();
           taskDriver.cl.jsEval("gotoFee_sheet();");
           
        }
        catch(Exception e)
        {
            System.out.print(e.toString());
        }
    }
}
