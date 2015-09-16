package edu.jsykora.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by jsykora on 11. 9. 2015.
 */
public class CustomElementLocator implements ElementLocator {
    private WebDriver driver;
    private FindBy annotation;

    public CustomElementLocator(Field upperElement, WebDriver driver) {
        this.driver = driver;
        this.annotation = upperElement.getDeclaredAnnotation(FindBy.class);
    }

    @Override
    public WebElement findElement() {
        return driver.findElement(resolveHow());
    }

    @Override
    public List<WebElement> findElements() {
        return driver.findElements(resolveHow());
    }

    private By resolveHow() {
        if (annotation.how() != null) {
            switch (annotation.how()) {
                case CLASS_NAME:
                    return By.className(annotation.className());
                case CSS:
                    return By.cssSelector(annotation.css());
                case ID:
                    return By.id(annotation.id());
                case ID_OR_NAME:
                /*this design would need some rework or find a work around*/
                    throw new NotImplementedException();
                case LINK_TEXT:
                    return By.linkText(annotation.linkText());
                case NAME:
                    return By.name(annotation.name());
                case PARTIAL_LINK_TEXT:
                    throw new NotImplementedException();
                case TAG_NAME:
                    return By.tagName(annotation.tagName());
                case XPATH:
                    return By.xpath(annotation.xpath());
                case UNSET:
                    throw new NotImplementedException();
            }
        }
        throw new NotImplementedException();
    }
}
