
-- 约数的学习
-- not null 指示某列不能存储 NULL
create database java;

use java;

-- 给 id 添加一个约数 ,不能为空
create table student(id int not null,name varchar(20));

insert into student values(1,'张三'),(2,'李四');

insert into student values(null,'王五');  -- 这里就会报错,因为约数了 不能为 null
-- ERROR 1048 (23000): Column 'id' cannot be null

select * from student;

--------------------------------------------------------------------

-- unique 保证某列的每行必须有唯一的值
create table A (id int unique,name varchar(20));

insert into A values(1,'张媛媛'),(2,'玉皇大帝');

insert into A values(1,'成吉思汗'); -- 这里会报错,id重复了
-- ERROR 1062 (23000): Duplicate entry '1' for key 'id'

select * from A;

--------------------------------------------------------------------

-- default 规定没有给列赋值时的默认值
create table B (id int , name varchar(20) default '默认值');

insert into B values(1,'张翼德'),(2,'刘玄德');

insert into B (id) values(3);

select * from B;

--------------------------------------------------------------------

-- primary key 主键约数.NOT NULL 和 UNIQUE 的结合
create table person (idCard int primary key, name varchar(20));

insert into person values(1,'祝融夫人'),(2,'古之恶来_典韦');

insert into person values(null,'李世民');    -- 报错,idCard 有主键约数,这里不能为 null
-- ERROR 1048 (23000): Column 'idCard' cannot be null

insert into person values(1,'迪丽热巴');     -- 报错,idCard 要有唯一性
-- ERROR 1062 (23000): Duplicate entry '1' for key 'PRIMARY'

select * from person;

drop table person;

create table person (id int primary key auto_increment, name varchar(20));    -- 这设置的就是一个自增主键

insert into person values (null,'陶渊明'),(null,'李清照'),(null,'武则天');

select * from person;

--------------------------------------------------------------------

-- foreign key 外键.保证一个表中的数据匹配另一个表中的值的参照完整性
-- 写法 foreign key (列名) references 表名(列名);
create table M (id int primary key auto_increment,class varchar(20));         -- 这里创建的是父表

insert into M values (null,'软件工程z1'),(null,'软件工程z2'),(null,'软件工程z3');

select * from M;

create table N (id int, name varchar(20), MId int,foreign key (MId) references M(id));   -- 这里创建的就是子表

insert into N values(1,'张三',1),(2,'李四',1),(3,'王五',2),(4,'赵六',3);

insert into N values(5,'路人甲',4);   -- 这里会报错 , 在 父表 中找不到 id 为 4 的 记录
-- ERROR 1452 (23000): Cannot add or update a child row:
-- a foreign key constraint fails (`java`.`n`, CONSTRAINT `n_ibfk_1` FOREIGN KEY (`MId`) REFERENCES `m` (`id`))

insert into N values(6,'炮灰乙',null); -- 这里是可以插入 null的

select * from M;

select * from N;

--------------------------------------------------------------------

-- 表的设计 : 三大范式 (一对一 / 一对多 / 多对多)

-- 举例 一对一: 身份证 和 人 . 造句 : 一个人只能对应一个身份证号,一个身份证号也只能对应一个人.

-- 举例 一对多: 班级 和 学生 . 造句 : 一个学生只能在一个班级里,一个班级里可以包含多个学生.

-- 举例 多对多: 课程 和 学生 . 造句 : 一个学生能选择多们课程,一门课程也可以有多个学生来上课.

-- ER图分为实体、属性、关系三个核心部分。实体是长方形体现，而属性则是椭圆形，关系为菱形。

--------------------------------------------------------------------

-- 插入查询结果 学习
create database if not exists java character set utf8;
use java;
create table student1 (id int , name varchar(20));

insert into student1 values (1,'张三'),(2,'李四'),(3,'王五');

create table student2 (id int , name varchar(20));

insert into student2 select * from student1;

select * from student1;

select * from student2;

drop table student2;

-- 通过指定列的形式来进行插入查询, 同理 select 后也可以接 表达式/去重/排序
create table if not exists student2 (name varchar(20) , id int);

insert into student2 select name,id from student1;

select * from student1;

select * from student2;