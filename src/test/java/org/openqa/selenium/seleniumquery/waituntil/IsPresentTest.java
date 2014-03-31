package org.openqa.selenium.seleniumquery.waituntil;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class IsPresentTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(IsPresentTest.class));
	}
	
	@Test
	public void isPresent__should_not_throw_an_exception_when_the_element_becomes_present__and__return_the_elements_val() throws Exception {
		// given
		// when
		$("div.clickable").click();
		// then
		assertEquals(0, $("input.ball").size());
		assertEquals("generated input starting value", $("input.ball").waitUntil.isPresent().val());
	}

}