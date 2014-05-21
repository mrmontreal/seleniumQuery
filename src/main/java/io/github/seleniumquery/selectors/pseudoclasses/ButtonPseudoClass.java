package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/button-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class ButtonPseudoClass implements PseudoClass {
	
	private static final ButtonPseudoClass instance = new ButtonPseudoClass();
	public static ButtonPseudoClass getInstance() {
		return instance;
	}
	private ButtonPseudoClass() { }
	
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return BUTTON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return (INPUT.equals(element.getTagName()) && BUTTON.equalsIgnoreCase(element.getAttribute("type")))
				||
			   BUTTON.equals(element.getTagName());
	}
	
	private static final CssFilter buttonPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :button is an extension selector, nobody implements it natively
		return CompiledCssSelector.createFilterOnlySelector(buttonPseudoClassFilter);
	}
	
}