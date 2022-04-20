
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

-- 举例 一对多: 班级 和 学生 . 造句 : 一个学生只能在一个班级里,一个班级里可以包含多个学生.     在建表时可以通过 设置外键的方式来建表

-- 举例 多对多: 课程 和 学生 . 造句 : 一个学生能选择多们课程,一门课程也可以有多个学生来上课.   在建表的时候可以 可以 构建一个 中间表 来实现

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

--------------------------------------------------------------------

-- 聚合查询  : 操作行和行之间的记录    不会统计null
-- count() 返回查询到的数据的 数量
-- sum() 返回查询到的数据的 总和，不是数字没有意义
-- avg() 返回查询到的数据的 平均值，不是数字没有意义
-- max() 返回查询到的数据的 最大值，不是数字没有意义
-- min() 返回查询到的数据的 最小值，不是数字没有意义

create table student (id int, name varchar(20));

insert into student values(1,'张三'),(2,'李四'),(3,'王五');

select count(*) from student;

select count(name) from student;

select sum(*) from student;

select avg(*) from student;

-- 同时聚合查询也可以配和 where 表达式 来使用
select min(id) from student where id > 1;

-- 同时聚合查询函数也可以进行 + - 操作等
select max(id)-min(id) from student;

--------------------------------------------------------------------

-- group by 分组
create table emp(
 id int primary key auto_increment,
 name varchar(20) not null,
 role varchar(20) not null,
 salary numeric(11,2)
);
insert into emp(name, role, salary) values
('马云','服务员', 1000.20),
('马化腾','游戏陪玩', 2000.99),
('孙悟空','游戏角色', 999.11),
('猪无能','游戏角色', 333.5),
('沙和尚','游戏角色', 700.33),
('隔壁老王','董事长', 12000.66);

-- 按role来分组,role的值相同为一组,不同为另一组
select role,max(salary),min(salary),avg(salary) from emp group by role;

-- group by 可以搭配 where 和 having 来使用
-- where : 表示先进行条件筛选,然后进行分组
-- having : 表示先进行分组,然后进行条件筛选
-- 同样的两个可以一起用

-- 单独用
select role,max(salary),min(salary),avg(salary) from emp where name != '马云' group by role;

select role,max(salary),min(salary),avg(salary) from emp group by role having avg(salary) < 10000;

-- 一起用  : 表示分组前去掉 马云 这条记录 , 分组后 去掉 平均薪资 大于 10000 的 , 同时 是 按 role 分组
select role,max(salary),min(salary),avg(salary) from emp where name != '马云' group by role having avg(salary) < 10000;

--------------------------------------------------------------------

-- 联合查询(多表查询) : 多表查询是对多张表的数据取笛卡尔积
-- 笛卡尔积 : 就是对任意的两张表进行 组合排列
create table student (id int , name varchar(20),classId int);

insert into student values(1,'张三',1),(2,'李四',1),(3,'王五',2);

create table class (classId int, name varchar(20));

insert into class values(1,'软件工程z1'),(2,'软件工程z2');

-- 笛卡尔积 操作
select * from student,class;

-- 联合查询操作
-- 方法一:
select * from student,class where student.classId = class.classId;
-- 方法二:
select * from student inner join class on student.classId = class.classId;       -- 内连接

-- 同时也可以指定 列 的方式
select student.name,class.name from student,class where student.classId = class.classId;


--------------------------------------------------------------------

-- 内连接
-- 一般我们在使用 多表查询 的时候,默认的都是内连接
select * from student,class where student.id = class.student_id;

select * from student inner join class on student.id = class.student_id;
-- 一般上面的 inner 可以省略

-- 外连接:
-- 左外连接
select * from student left join class on student.id = class.student_id;

-- 右外连接
select * from student right join class on student.id = class.student_id;

-- 自连接 : 自己对自己进行查询
select * from student as s1,student s2 where student.id = 3;

-- 注意我们在使用自连接时,应该需要起别名
select s1.name,s2.id from student as s1,student as s2 where student.id = 3;

--------------------------------------------------------------------

-- 子查询 : 就是套娃操作
select * from student where classId = (select id from classId );

-- in 子查询也可以搭配 in 来使用
select * from score where course_id in (select id from course where name = '语文' or name = '英文');

--------------------------------------------------------------------

-- 合并查询 (union/union all) : 前一个自动去重,后一个不会
-- 如果在一张表中,效果和 or 是一样的(完全等价)
select * from student where id = 3 union select * from student where id = 4;
select * from student where id = 3 or id = 4;

-- 多张表的使用方式
select * from student where id = 3 union select * from score where chinese > 60;
