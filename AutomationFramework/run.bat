set projectlocation=C:\Users\Pristine\eclipse-workspace\AutomationFramework

cd %projectlocation%

set classpath=%projectlocation%\bin;%projectlocation%\lib\*

java org.testng.TestNG %projectlocation%\testng.xml

pause