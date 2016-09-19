dataloader是一个eclipse工程项目，使用了多个第三方的类库文件。

它实现了读取数据并以表格和统计图形进行输出的功能。

1.由impl.TextDataLoader来实现将文本文件中的内容读取并进行处理。
2.由impl.XMLDataLoader类实现读取XML文件中的数据。
3.由impl.ExcelDataLoader类实现读取一个Excel文件中的数据。
4.由impl.DatabaseDataLoader类实现读取数据库中的数据。
5.由impl.NetDataLoader类实现读取来自网络服务器提供的数据。

运行该程序之前，请先阅读setup.sql文件，需要在mysql数据库中创建student表。

通过配置loader.properties文件，可以设置不同的数据读取方式。

如果要读取来自网络服务器提供的数据，需要先启动serv.DataServer

该工程使用jfreechart这个开源项目实现图表的输出。


