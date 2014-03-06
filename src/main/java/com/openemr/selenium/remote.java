/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openemr.selenium;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Point;
import java.net.URL;


/**
 *
 * @author yehster
 */

class wd extends RemoteWebDriver
{
    public wd(URL server, Capabilities dc)
    {
        //
//        super(server,dc);
        super(server,dc);
//            super();
            startClient();
//        super.quit();
        setSessionId("51ecee18-0fa4-4ffd-8a81-add167dcafcc");
        System.out.println(this.getCurrentUrl());
    }

}
public class remote {

    static public WebDriver build(String srv) throws Exception
    {
        Capabilities dc = DesiredCapabilities.firefox();
        RemoteWebDriver rv = new RemoteWebDriver(new URL(srv),dc);
//        WebDriver rv=new org.openqa.selenium.firefox.FirefoxDriver();
        rv.manage().window().setPosition(new Point(880,-1200));
        rv.manage().window().maximize();
        
        return rv;
        
    }
}
