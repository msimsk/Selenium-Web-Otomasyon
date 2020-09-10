package com.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {
    private WebDriver driver;
    private SeleniumWebOtomasyon webOtomasyon;

    @Before
    public void setup() throws Exception{
        String pathToChromeDriver = "lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        driver = new ChromeDriver();
    }

    @Test
    public void testOne(){
        //anasayfa kontrolü
        webOtomasyon = new SeleniumWebOtomasyon(driver);
        String mainPageControl = webOtomasyon.HomePageControl();
        System.out.println(mainPageControl);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}

        //giriş
        String login = webOtomasyon.Login();
        System.out.println(login);

        //bilgisayar arama
        String search = webOtomasyon.SearchPC();
        System.out.println(search);

        //sepete ekleme
        String[] price = webOtomasyon.AddItemsBasket();
        System.out.println(price[1] + "\nfiyatı: " + price[0]);

        //Fiyat Kontrol
        String priceControl = webOtomasyon.PriceControl(price[0]);
        System.out.println(priceControl);

        //Ürün adet arttırma
        String count = webOtomasyon.AdetArttirma();
        System.out.println(count);

        //Sepeti temizleme
        String basket = webOtomasyon.BasketEmpty();
        System.out.println(basket);
    }

    @After
    public void teardown() throws Exception{
        driver.close();
    }
}
