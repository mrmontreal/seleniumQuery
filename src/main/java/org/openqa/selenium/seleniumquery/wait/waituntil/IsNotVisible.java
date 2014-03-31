package org.openqa.selenium.seleniumquery.wait.waituntil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class IsNotVisible {
	
	public static void isNotVisible(final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				for (WebElement webElement : seleniumQueryObject) {
					if (webElement.isDisplayed()) {
						return false;
					}
				}
				return true;
			}
		}, "to have all elements not be not visible.");
	}

}