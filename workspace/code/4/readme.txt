calc是一个eclipse工程文件。
进入eclipse后，导入该工程。
右键点击com.abc.calc包，然后export导出，
在导出窗口中选择输出Java--->JAR file
然后点击下一步，选中Export generated class files and resources.
指定JAR file： 例如：calc\lib\c.jar
然后选中窗口下方的Options，
最后直接点击完成按钮（Finish）

一样的道理，可以导出com.abc.test这个包，生成calc\lib\t.jar
也可以将这两个包同时输出到一个JAR文件中，
右键点击src，Export输出，Java--->JAR file，例如calc\lib\calc.jar


priv是一个eclipse工程，该工程是用来说明访问权限的程序。
exceptionhandling是一个eclipse工程，该工程是用来说明异常处理的程序。
constructor是一个eclipse工程，该工程是用来说明构造方法的程序。
abstract是一个eclipse工程，该工程是用来说明抽象类和抽象方法的程序。
interface是一个eclipse工程，该工程是用来说明接口的程序。
override1-4是四个eclipse工程，这些工程是用来说明覆盖的程序。
innerclass是一个eclipse工程，该工程是用来说明内部类的程序。
CastAndPromotion是一个eclipse工程，该工程是用来说明转换和提升的程序。
