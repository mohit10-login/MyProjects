set projectLocation=D:\Mywork\MyProjects\DataDrivenFrameWork
echo %projectLocation%
cd %projectLocation%
set classpath=%projectLocation%\src\test\java\TestCases;%projectLocation%\*
java org.testng.TestNG %projectLocation%\src\test\java\Runner\TestNG.xml
pause