package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SeleniumWebOtomasyon {
    private WebDriver webDriver;

    public SeleniumWebOtomasyon(WebDriver driver) {
        webDriver = driver;
    }

    public String HomePageControl(){
        webDriver.navigate().to("https://www.trendyol.com/");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}
        String mainControl = webDriver.findElement(By.id("popupContainer")).getText();
        if (mainControl.isEmpty()) {
            mainControl = "Anasayfada";
        } else {
            mainControl = "Anasayfada değil";
        }
        webDriver.findElement(By.className("fancybox-close")).click();
        return mainControl;
    }

    public String Login (){
        webDriver.findElement(By.xpath("//*[@id=\"accountBtn\"]/div[1]/i")).click();
//        webDriver.findElement(By.id("not-logged-in-container")).click();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}
        webDriver.findElement(By.id("email")).sendKeys("trendyoltestinium@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("qwertfdsa2020");
        webDriver.findElement(By.id("loginSubmit")).click();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}

        //kontrol
        String loginControl = webDriver.findElement(By.id("logged-in-container")).getText();
        assertThat(loginControl, is("Hesabım"));
        return "Giriş Başarılı";
    }

    public String SearchPC (){
        webDriver.findElement(By.className("search-box")).sendKeys("bilgisayar");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        webDriver.findElement(By.className("search-icon")).click();
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){}
        return "Arama Yapıldı";
    }

    public String[] AddItemsBasket(){
        List<WebElement> result = webDriver.findElements(By.className("p-card-wrppr"));
        Random random = new Random();
        result.get(random.nextInt(result.size())).click();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}

        WebElement element = webDriver.findElement(By.cssSelector("button.add-to-bs"));
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);
//        webDriver.findElement(By.cssSelector("button.add-to-bs")).click();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}
        String[] price = new String[2];
        String[] priceSplit = webDriver.findElement(By.className("pr-bx-dsc")).getText().split("\n");
        System.out.println(priceSplit[priceSplit.length - 1]);
        price[0] = priceSplit[priceSplit.length - 1];
        price[1] = "Sepete Eklendi";
        return price;
    }

    public String PriceControl(String beforePrice){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}
//        webDriver.findElement(By.xpath("//*[@id=\'myBasketListItem\']/div[1]/a")).click();
        webDriver.navigate().to("https://www.trendyol.com/sepetim#/basket");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}
        String basketPrice = webDriver.findElement(By.className("pb-basket-item-price")).getText();
        String[] array = basketPrice.split("\n");
        System.out.println("Sepet Fiyatı: " + array[array.length-1]);
        assertThat(array[array.length-1], is(beforePrice));
        return "Fiyat Aynı";
    }

    public String AdetArttirma(){
        webDriver.findElement(By.xpath("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/div[1]/div/button[2]")).click();
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){}
        String count = webDriver.findElement(By.xpath("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/div[1]/div/input")).getAttribute("value");
        String[] array = count.split(" ");
        assertThat(count, is("2"));
        return "Ürün sayısı: 2";
    }

    public String BasketEmpty (){
        List<WebElement> result = webDriver.findElements(By.cssSelector("div.pb-merchant-group"));
        for (int i = 0; i < result.size(); i++){
            webDriver.findElement(By.cssSelector("i.i-trash")).click();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){}
            webDriver.findElement(By.className("btn-remove")).click();
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){}
        }
        return "Sepet boşaltıldı";
    }
}
