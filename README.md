<b>Automation Framework – Hybrid for XE Currency Converter</b><br>
Document Ver. 1.0<br>
Created By- Pranit Kurdukar (Pranit.kurdukar@gmail.com)<br>
01/11/2020<br>
<b>Tools and PlugIns-</b><br>
The following tools and plugins are required to use automation framework<br>
1.	Eclipse IDE<br>
2.	JDK 1.8 or beyond<br>
3.	Apache Maven 3.6.3<br>
Setup environment variables after downloading above tools/plugsin.<br>
<b>Download the framework from GITHUB public repository-</b><br>
Download .zip version of Maven Project from github shared repository to your local machine.<br>
GitHub URL: <br>
https://github.com/pranitkurdukar/XE-Currency-Converter.git<br>
<b>Overview of framework-</b><br>
<b>Run the test suite in Eclipse IDE-</b><br>
Follow the below steps to run the test case.<br>
1. Right click on Maven Project – XE-CURRENCY-CONVERTER and Click Update Project (Alt + F5)<br>
2.	After updating Maven Project, right click on project name and Run it as Maven Install to download all dependencies at local machine<br>
3.	Update the test data in testdata sheet which is kept in the folder XE-CURRENCY-CONVERTER\XE-CURRENCY-CONVERTER\src\test\resources\excel\testdata.xlsx. Make sure type of cell should be TEXT before entering test data in the excel.<br>
4.	Save all files<br>
5.	Right Click on Maven Project, Run\clean<br>
6.	Right Click on Maven Project, Run\test<br>
7.	Test will run on chrome browser<br>
8.	To run the same test on different browser, uncomment reference of testng xml files in pom.xml. <br>
9.	Extent Report will be generated in the folder \XE-CURRENCY-CONVERTER\XE-CURRENCY-CONVERTER\target\surefire-reports\html\Index.html .  <br>
<b>Run Test Suite on Windows Command Prompt-</b><br>
1.	Open windows command prompt<br>
2.	Navigate to project folder using command<br>
c:<br>
cd\<br>
cd <<project folder path>><br>
3.	Run the following command, after entering below command, test script will start to run in chrome browser. <br>
mvn clean test -DsuiteXmlFil=src/test/resources/runners/testng.XE_Chrome.functional.xml<br>
4.	Similar command can be used to run the test suite in ie and firefox browser<br>
mvn clean test -DsuiteXmlFil=src/test/resources/runners/testng.XE_IE.functional.xml<br>
mvn clean test -DsuiteXmlFil=src/test/resources/runners/testng.XE_Firefox.functional.xml<br>
