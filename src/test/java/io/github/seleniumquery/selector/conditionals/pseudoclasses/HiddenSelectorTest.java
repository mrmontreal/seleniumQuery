package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class HiddenSelectorTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void hiddenPseudoClass() throws Exception {
    	assertThat($("#visibleDiv").is(":hidden"), is(false));
    	assertThat($("#visibleDiv2").is(":hidden"), is(false));
    	assertThat($("#invisibleDiv").is(":hidden"), is(true));
    	
    	assertThat($("#invisibleParentDiv").is(":hidden"), is(true));
    	assertThat($("#almostVisibleDiv").is(":hidden"), is(true));
    }

}