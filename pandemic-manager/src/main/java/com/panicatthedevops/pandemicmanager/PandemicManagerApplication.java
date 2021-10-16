package com.panicatthedevops.pandemicmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class PandemicManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PandemicManagerApplication.class, args).close();
		Scanner in = new Scanner(System.in);
		WebDriver driver = new HtmlUnitDriver();

		driver.get("https://giris.turkiye.gov.tr/Giris/gir");
		System.out.println("Enter your TCKN:");
		driver.findElement(By.id("tridField")).sendKeys(in.nextLine());
		System.out.println("Enter your E-Devlet password:");
		driver.findElement(By.id("egpField")).sendKeys(in.nextLine());
		driver.findElement(By.name("submitButton")).click();

		driver.get("https://www.turkiye.gov.tr/saglik-bakanligi-hes-kodu-sorgulama");
		System.out.println("Enter your HES code:");
		driver.findElement(By.id("hes_kodu")).sendKeys(in.nextLine());
		driver.findElement(By.className("actionButton")).click();

		driver.get("https://www.turkiye.gov.tr/saglik-bakanligi-hes-kodu-sorgulama?sonuc=Goster");
		List<WebElement> elements = driver.findElements(By.xpath("//dl"));	// TODO: Burayı kontrol etmek lazım her demodan önce.
		for (WebElement element: elements) {
			System.out.println(element.getText());
		}
		driver.quit();
	}
}
