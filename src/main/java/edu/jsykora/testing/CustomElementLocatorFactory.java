package edu.jsykora.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Created by jsykora on 11. 9. 2015.
 */
public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final WebDriver driver;

    public CustomElementLocatorFactory(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new CustomElementLocator(field, driver);
    }
}
