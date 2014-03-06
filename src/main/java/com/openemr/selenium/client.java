/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openemr.selenium;


import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 *
 * @author yehster
 */
public class client {
    protected WebDriver wd;
    protected String user;
    protected String password;
    protected String serverURL;
    
    public client(WebDriver wd,String server, String user, String password)
    {
        this.wd=wd;
        this.serverURL=server;
        this.user=user;
        this.password=password;
        
    }
    
    public WebElement byName(String name)
    {
        return this.wd.findElement(By.name(name));
    }

    public WebElement byCSS(String css)
    {
        return this.wd.findElement(By.cssSelector(css));
    }
    
    public void menuClick(String css)
    {
        this.wd.switchTo().defaultContent();
        this.wd.switchTo().frame("left_nav");
        this.wd.findElement(By.cssSelector(css)).click();
        
    }
    
    public WebElement setField(String css,String value)
    {
        WebElement el=this.byCSS(css);
        el.sendKeys(value);
        return el;
    }
    
    public void switchToMain()
    {
        this.wd.switchTo().defaultContent();
        this.wd.switchTo().frame("RTop");
    }
    
    public WebElement setSelect(String css,String value)
    {
        String option=css + "> option[value='"+value+"']";
        WebElement el=this.byCSS(option);
        el.click();
        return el;
    }
    public void switchToPopup()
    {
        Set<String> handles=this.wd.getWindowHandles();
        this.wd.switchTo().window((String)handles.toArray()[1]);
    }
    public void login()
    {
        this.wd.get(this.serverURL);
        this.wd.switchTo().frame("Login");
        this.byName("authUser").sendKeys(this.user);
        this.byName("clearPass").sendKeys(this.password);
        this.byCSS("input[value='Login']").click();
    }
    public WebDriver wd()
    {
        return this.wd;
    }
}
