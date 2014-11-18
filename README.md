#[seleniumQuery](http://seleniumquery.github.io) - Cross-Driver Selenium Java interface

###Cross-Driver (Cross-Browser) jQuery-like native Java interface for Selenium WebDriver

seleniumQuery is a Java library/framework that intends to bring a "cross-driver" (cross-browser) **jQuery-like interface in** (pure) **Java** for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example snippet:

```java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
```

Allows querying elements by:

- **CSS Selectors** - `$(".myClass")`,
- **jQuery/Sizzle enhancements** - `$(".myClass:eq(3)")`, `$(".myClass:contains('My Text!')")`
- **XPath** - `$("//div/*/label/preceding::*")`
- and even some own **seleniumQuery selectors**: `$("#myOldDiv").is(":not(:present)")`.

Built using Selenium WebDriver's native capabilities **only**:

- No `jQuery.js` is embedded at the page, no side-effects are generated;
    - Doesn't matter if the page uses jQuery or not (or even if the JavaScript global variable `$` is other library like `Prototype.js`).
- Capable of handling/testing **JavaScript-disabled pages**
    - Test pages that use [Unobtrusive JavaScript](http://en.wikipedia.org/wiki/Unobtrusive_JavaScript).
    - Most functions don't even require the browser/driver to have JavaScript enabled!

##Quickstart: A running example

Try it out now with the running example below:

```java
import static io.github.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

public class SeleniumQueryExample {
    public static void main(String[] args) {
        // sets Firefox as the global driver
        $.browser.globalDriver().useFirefox();
        
        $.browser.open("http://www.google.com");
        
        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();

        String resultsText = $("#resultStats").text();
        System.out.println(resultsText);

        // Besides the short syntax and the jQuery behavior you already know,
        // other very useful function in seleniumQuery is .waitUntil(),
        // especially handy for handling/testing Ajax enabled pages:
        
        $("input[name='q']").waitUntil().is(":enabled");
        // The line above waits for no time, as that input
        // is always enabled in google.com.

        $.browser.quit(); // quits the global (firefox) driver
    }
}
```
To get seleniumQuery's latest snapshot, add this to your **`pom.xml`**:

```xml
<!-- The project dependency -->
<dependencies>
    <dependency>
        <groupId>io.github.seleniumquery</groupId>
        <artifactId>seleniumquery</artifactId>
        <version>0.9.0-SNAPSHOT</version>
    </dependency>
</dependencies>
<!-- The repository the snapshots will be downloaded from.
    Can either go in your pom.xml or settings.xml -->
<repositories>
	<repository>
		<id>sonatype-nexus-snapshots</id>
		<name>Sonatype Nexus Snapshots</name>
		<url>https://oss.sonatype.org/content/repositories/snapshots/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
```

#Features

###Readable jQuery syntax code you already know

Make your code/tests more readable and easier to maintain. Leverage your knowledge of jQuery.

```java
// Instead of regular Selenium code:
WebElement element = driver.findElement(By.id("mySelect"));
new Select(element).selectByValue("ford");

// You can have the same effect writing just:
$("#mySelect").val("ford");
```


###Waiting capabilities for improved Ajax testing

Other important feature is the leverage of `WebDriver`'s `FluentWait` capabilities **directly** in the element (so long, boilerplate code!) through the use of the `.waitUntil()` function:

```java
/*
 * Selenium WebDriver cannot natively detect the end of an Ajax call.
 * To test your application behaviour, you can and should work with the Ajax's expected effects.
 * 
 * Below is an example of a <div> that should be hidden as effect of an Ajax call.
 * The code will only continue after it is gone. If not, it will throw a timeout exception.
 */
$("#ajaxDiv").click();
$("#ajaxDiv").waitUntil().is(":not(:visible)");

// Or, fluently:
$("#ajaxDiv").click().waitUntil().is(":not(:visible)");
```

And, yeah, that's right, the `.is()` function above is your old-time friend that takes a selector as argument!

#API

For the currently implemented jQuery functions check the [supported list](#supported-jquery-functions).

In order to handle interactions with Ajax-enabled pages, you can use the `.waitUntil()` function:

- The `.waitUntil()` functions will *requery* the DOM for the elements until the given condition is met, returning a **new** seleniumQuery object when that happens.

```java
// .waitUntil() will requery the DOM every time until the matched set fulfills the requirements

// .is() functions
$(".aDivDiv").waitUntil().is(":present");
$(".myInput").waitUntil().is(":enabled");
$(".aDivDiv").waitUntil().is(":visible");
$(".myInput").waitUntil().is(":visible:enabled");
// functions such as .val(), .text() and others are also available
$(".myInput").waitUntil().val().isEqualTo("expectedValue");
$(".aDivDiv").waitUntil().text().contains("expectedText");
// and more...
$(".myInput").waitUntil().val().matches(".*\d{10}\*");
$(".myInput").waitUntil().size().isGreaterThan(7);
$(".aDivDiv").waitUntil().html().contains("<div>expected</div>");
```

Global object (static) functions:

- `$.browser.openUrl("http://www.url.to.go.com");`: Opens a URL
- `$.browser.openUrl(new File("path/to/localFile.html"));`: Opens a local file
- `$.browser.setDefaultBrowser(webDriver);`: Sets the browser to be used by `$(".selector")`
- `$.browser.sleep(10, TimeUnit.SECONDS);`: Instructs the browser (thread) to wait (sleep) for the given time.

###Alternate symbols

If the dollar symbol, `$`, gives you the yikes -- we know, it is used for internal class names --, it is important to notice that the `$` symbol in seleniumQuery is not a class name, but a `static` method (and field) imported statically. Still, if you don't feel like using it, you can resort to `sQ()` or good ol' `jQuery()` and benefit from all the same functions:

```java
import static io.github.seleniumquery.SeleniumQuery.sQ;
import static io.github.seleniumquery.SeleniumQuery.jQuery;
...
String oldStreet = sQ("input.street").val();
sQ("input.street").val("4th St!");

String oldStreetz = jQuery("input.street").val();
jQuery("input.street").val("5th St!");
```

#CSS and jQuery Extension Selectors

seleniumQuery allows querying elements by XPath, CSS3 selectors, jQuery/Sizzle extensions and even some exclusive selectors. Find more about them in [seleniumQuery Selectors wiki page.](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-Selectors)

#seleniumQuery API: jQuery, waitUntil and other functions

seleniumQuery aims to implement all relevant jQuery functions, as well as adding some of our own.

Our main goals is emulating user actions and "sensing" the pages, currently our intention is to implement functions that read the state of the page and allow intuitive form manipulation.

Get to know what jQuery functions seleniumQuery supports and what else it brings to the table on our [seleniumQuery API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).


