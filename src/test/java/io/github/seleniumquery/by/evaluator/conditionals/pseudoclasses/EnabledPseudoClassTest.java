package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class EnabledPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void enabledPseudo_with_tag_button() {
		assertSelectorMatchedSetSize("button:enabled", 1);
	}
	
	@Test
	public void enabledPseudo_with_tag_input() {
		assertSelectorMatchedSetSize("input:enabled", 24);
	}
	
	@Test
	public void enabledPseudo_with_tag_select() {
		assertSelectorMatchedSetSize("select:enabled", 1);
	}
	
	@Test
	public void enabledPseudo_with_tag_option() {
		if ($.browser.getDefaultDriver() instanceof HtmlUnitDriver) {
			// TODO this is a known HtmlUnit bug! See issue #3
			assertSelectorMatchedSetSize("option:enabled", 9);
		}
		else {
			assertSelectorMatchedSetSize("option:enabled", 3);
		}
	}
	
	@Test
	public void enabledPseudo_with_tag_optgroup() {
		assertSelectorMatchedSetSize("optgroup:enabled", 3);
	}
	
	@Test
	public void enabledPseudo_with_tag_textarea() {
		assertSelectorMatchedSetSize("textarea:enabled", 1);
	}
	
	@Test
	public void enabledPseudo() {
		if ($.browser.getDefaultDriver() instanceof HtmlUnitDriver) {
			// TODO see enabledPseudo_with_tag_option()
			assertSelectorMatchedSetSize(":enabled", 39);
		}
		else {
			assertSelectorMatchedSetSize(":enabled", 33);
		}
	}

	private void assertSelectorMatchedSetSize(String selector, int size) {
		List<WebElement> elements = SeleniumQueryCssCompilerIntegrationTest.compileAndExecute(selector);
		
		assertThat(elements, hasSize(size));
	}
	
}