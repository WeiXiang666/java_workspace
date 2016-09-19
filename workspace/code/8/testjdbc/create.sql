grant all on *.* to java@localhost identified by 'java1234';
use test;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `java` int(11) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

insert into student(id,name,email,age,java,gender) values('001', '’≈»˝','zs@abc.com', 20, 98, 1);
insert into student(id,name,email,age,java,gender) values('002', '¿Óƒ»','ln@abc.com', 20, 99, 0);