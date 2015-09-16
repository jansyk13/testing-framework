# Selenium Based Automation Testing Framework

Simple framework built on top of Selenium to make writing automation testing code easier. Framework consists of several classes that provide utility functions. It is recommended to use Page Object and Page Factory patterns alongside this project.

Example:
```
@FindBy(id = "country")
@WebElementFinder
private Select country;
```
When using plain Selenium you can only use annotation @FindBy with WebElements, my frameworks allows you to use another annotation(@WebElementFinder), that looks for WebElement as property of annotated object.

For this you have to use CustomFieldDecorator and CustomElementLocatorFactory:
```
PageFactory.initElements(
      new CustomFieldDecorator(
          new CustomElementLocatorFactory(Browser.driver()), Browser.driver()), page)
```
