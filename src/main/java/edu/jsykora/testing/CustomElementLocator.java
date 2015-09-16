package edu.jsykora.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by jsykora on 11. 9. 2015.
 */
public class CustomElementLocator implements ElementLocator {
    private final WebDriver driver;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;
    private final boolean shouldCache;

    public CustomElementLocator(Field upperElement, WebDriver driver) {
        this.driver = driver;
        Annotations annotations = new Annotations(upperElement);
        this.shouldCache = annotations.isLookupCached();
        this.by = annotations.buildBy();
    }

    @Override
    public WebElement findElement() {
        if (cachedElement != null && shouldCache) {
            return cachedElement;
        }
        WebElement element = driver.findElement(by);
        if (shouldCache) {
            cachedElement = element;
        }
        return element;
    }


    @Override
    public List<WebElement> findElements() {
        if (cachedElementList != null && shouldCache) {
            return cachedElementList;
        }
        List<WebElement> elements = driver.findElements(by);
        if (shouldCache) {
            cachedElementList = elements;
        }
        return elements;
    }


}
