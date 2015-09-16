package edu.jsykora.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CustomFieldDecorator extends DefaultFieldDecorator implements FieldDecorator {
    private WebDriver driver;

    public CustomFieldDecorator(ElementLocatorFactory factory, WebDriver driver) {
        super(factory);
        this.driver = driver;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (field.isAnnotationPresent(WebElementFinder.class) && field.isAnnotationPresent(FindBy.class)) {
            Class<?> clazz = null;
            Constructor<?> cons = null;
            WebElementFinder finder = field.getDeclaredAnnotation(WebElementFinder.class);
            field.setAccessible(true);

            try {
                clazz = field.getType();
                cons = clazz.getConstructor(WebElement.class);
            } catch (NoSuchMethodException | SecurityException | IllegalArgumentException e1) {
                return super.decorate(loader, field);
            }

            Field webElementField = null;
            Class<?> helperClass = clazz;
            while (webElementField == null) {
                try {
                    webElementField = helperClass.getDeclaredField(finder.target());
                    webElementField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    helperClass = helperClass.getSuperclass();
                }
                if (!(helperClass instanceof Class)) {
                    return null;
                }
            }

            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            WebElement element = proxyForLocator(loader, locator);

            try {
                return cons.newInstance(element);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }


        } else if ((WebElement.class.isAssignableFrom(field.getType())
                || isDecoratableList(field))) {
            ElementLocator locator = new DefaultElementLocatorFactory(driver).createLocator(field);
            if (locator == null) {
                return null;
            }

            if (WebElement.class.isAssignableFrom(field.getType())) {
                return proxyForLocator(loader, locator);
            } else if (List.class.isAssignableFrom(field.getType())) {
                return proxyForListLocator(loader, locator);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}