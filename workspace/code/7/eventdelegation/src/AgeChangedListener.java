//下面是事件的听众接口，定义了和听众对象之间的方法调用约定
//所有听众接口（监听接口）必须继承接口java.util.EventListener
//该接口是听众接口的根接口，该接口中没有定义任何方法，这样的
//接口也称为标签接口（tagging interface），和java.io.Serializable接口一样
//都是标签接口。

public interface AgeChangedListener extends java.util.EventListener {
	public void processAgeChanged(AgeChangedEvent ace);
}
