Information to run this assignment:

UI Scenario :  Script will run in both browser(chrome and firefox)

1. Testng XML : MobilePurchaseBooking.xml (under suite folder)
2. Test script written under MobilePurchase.java file.

run from command line : 
Headless :: xvfb-run --auto-servernum --server-args="-screen 0 1024x768x24" mvn clean package -Dsuite.name=suites/MobilePurchaseBooking.xml
Headfull :: mvn clean package -Dsuite.name=suites/PillowPurchaseBooking.xml

find reportng report under this path : target/surefire-reports/html/index.html 



