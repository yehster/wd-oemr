/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openemr.selenium;


import java.util.Set;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    
    public List<WebElement> elemsCSS(String css)
    {
        return this.wd.findElements(By.cssSelector(css));
    }
    public void menuClick(String css)
    {
        this.wd.switchTo().defaultContent();
        this.wd.switchTo().frame("left_nav");
        WebDriverWait wdw = new WebDriverWait(wd,10);
        WebElement elem=this.wd.findElement(By.cssSelector(css));
        wdw.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
        
    }
    
    public void menuSection(String title)
    {
    this.wd.switchTo().defaultContent();
    this.wd.switchTo().frame("left_nav");        
       List<WebElement> Sections= this.wd.findElements(By.cssSelector("#navigation > li > span"));
       for(WebElement element: Sections)
       {
           if(element.getText().equals(title))
           {
               System.out.println(title);
               element.click();
           }
       }
    }
    
    public void menuForm(String formname)
    {
        this.wd.switchTo().defaultContent();
        this.wd.switchTo().frame("left_nav");
        WebDriverWait wdw = new WebDriverWait(wd,10);
        String onclick="return loadFrame2('cod2','RBot','patient_file/encounter/load_form.php?formname=";
        String css=("a[onclick=\""+onclick+formname+"')\"]");
        WebElement elem=this.wd.findElement(By.cssSelector(css));
        wdw.until(ExpectedConditions.elementToBeClickable(elem));        
        elem.click();        
    }
    
    public WebElement waitFor(String css)
    {
        WebDriverWait wait=new WebDriverWait(this.wd,10);
        WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
        return element;
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
    
    public WebElement setSelectByContent(String css,String value)
    {
        WebElement el=this.byCSS(css);
        List<WebElement> options=el.findElements(By.cssSelector("option"));
        for(WebElement option: options)
        {
            if(option.getText().equals(value))
            {
                option.click();
            }
        }
        
        return el;
    }    
    public void switchToPopup()
    {
        Set<String> handles=this.wd.getWindowHandles();
        this.wd.switchTo().window((String)handles.toArray()[1]);
    }
    
    public void switchToTop()
    {
        Set<String> handles=this.wd.getWindowHandles();
        this.wd.switchTo().window((String)handles.toArray()[0]);
    }    
    
    public void switchToEncounter()
    {
        this.wd.switchTo().defaultContent();
        this.wd.switchTo().frame("RBot");
    }
    public void login()
    {
        this.wd.get(this.serverURL);
        this.wd.switchTo().frame("Login");
        this.byName("authUser").sendKeys(this.user);
        this.byName("clearPass").sendKeys(this.password);
        this.byCSS("input[value='Login']").click();
        
        this.switchToMain();
    }
    public WebDriver wd()
    {
        return this.wd;
    }
}
