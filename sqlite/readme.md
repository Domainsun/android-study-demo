
> ####  1.前言
刚开始学安卓开发的在课堂及书本上了解过sqlite，但嫌sqlite太老太繁琐后来在公司中一直使用的是`realm`。无奈公司有的老项目是用的sqlite，所以再抽空学习一下。

---

> #### 2.介绍
介绍没啥好说的，简单来说就是一个本地的轻量级关系型数据库，最后是以文件的形式存储在本地的。

---

> #### 3. 使用
了解下来，sqlite的使用也很简单，主要是要会sql语句。


> #### 3.1 建库、建表
android sdk 提供了一个抽象类SQLiteOpenHelper来帮助我们使用sqlite，我们只需要继承它，实现抽象方法就可以使用sqlite啦。


![image](https://s2.ax1x.com/2019/12/01/QeIxRP.png)

**解释:**<br>

- **onCreate**:SQLiteOpenHelper的抽象方法，数据库第1次创建时则会调用，即第1次调用 getWritableDatabase / getReadableDatabase时调用。需要这里执行数据库建表语句。
- **onUpgrade**： 数据库版本升级的时候会调用，也是通过getWritableDatabase/ getReadableDatabase 时，发现本地数据库和当前数据库版本不一致触发 可以根据传进来的oldVersion 版本号来对本地数据库结构升级。
- **getReadableDatabase&getWritableDatabase**:。获取用来操作对表增删改查时的对象，都可创建或打开现有数据库（数据库已经存在则直接打开，没有则创建）,用于读/写数据库。区别在于磁盘空间满了的时候，数据库不可写用getWritableDatabase打开就会直接报错，但getReadableDatabase打开失败后会尝试以只读方式打开。

> #### 3.2 DML - 数据库操作语言
> #### 3.2.1 增加

```java
 public void insertUser(User user) {

//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", user.getName());
//        contentValues.put("age", user.getAge());
//        db.insert(TB_NAME, null, contentValues);

        //使用sql语句增加
        String version2Sql = String.format("insert into user (age,name,sex) values (%d,'%s',%d)",user.getAge(),user.getName(),1);
        db.execSQL(version2Sql);
    }
```
**解释:**<br>
- **db**: getWritableDatabase获得的数据表操作的SQLiteDatabase对象。
- **db.insert**：通过调用insert方法，对数据表新增一条数据。传入表名，以及对于的字段值，字段值通过ContentValues来传入，也是一个键值对对象。中间的null是指:ContentValues为空的时候的字段值，传null就好。
- **db.execSQL**：通过执行sql语句，执行新增，下面的删除和更改数据同样都是传入对于的删除和更改sql语句。
> #### 3.2.2 删除
```java
    public void delByName(String name) {
//        db.delete(TB_NAME, "name = ?", new String[]{name});

        //使用sql语句删除
        String sql = String.format("delete from user where name='%s'",name);
        db.execSQL(sql);
    }
```
**解释:**<br>
- **db.delete**:调用delete方法删除表记录， 传入表名，条件语句，条件语句中`?`的具体值。 注意中间的条件语句的编写规则就是去掉正常sql语句中where。
> #### 3.2.3 更改
```
public void updateByName(User user, String name) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", user.getName());
//        contentValues.put("age", user.getAge());
//        db.update(TB_NAME, contentValues, "name = ?", new String[]{name});

        //使用sql语句更改
        String sql = String.format("update user set age=%d , name='%s' where name='%s'",user.getAge(),user.getName(),name);
        db.execSQL(sql);
    }
```
**解释:**<br>
- **db.update**：传入表名，更改后字段值，条件语句，条件语句中`?`的具体值。
> #### 3.3 查询
```
public List<User> queryByName(String name) {
//        Cursor cursor = db.query(TB_NAME, null, "name = ?", new String[]{name}, null, null, null);

        //使用sql语句查询
        Cursor cursor=db.rawQuery("select * from user where name = ? ",new String[]{name});

        List<User> orderList = new ArrayList<User>(cursor.getCount());
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String newName = cursor.getString(cursor.getColumnIndex("name"));
                Integer newAge = cursor.getInt(cursor.getColumnIndex("age"));
                User order = new User(newName, newAge);
                orderList.add(order);
            }
        }
        cursor.close();
        return orderList;
    }
```
**解释:**<br>
- **db.rawQuery**: 通过sql语句查询，传入sql语句和语句中`?`代表的实际值的数组。
- **db.query**: 通过调用query方法执行查询,入参如下:<BR>


| 参数     | 意思                             |
| --------- | ------------------------------------------- |
| table     | 要操作的表                             |
| columns   | 查询的列所有名称集                 |
| selection | WHERE之后的条件语句，可以使用占位符 |
| groupBy   | 指定分组的列名                       |
| having    | 指定分组条件，配合groupBy使用    |
| orderBy   | 指定排序的列名                       |
| limit     | 指定分页参数                          |
| distinct  | 可以指定“true”或“false”表示要不要过滤重复值 |
- **Cursor**: Cursor是一个游标接口，提供了遍历查询结果的方法，如移动指针方法move()，获得列值方法。Cursor游标常用方法如下：
![image](https://upload-images.jianshu.io/upload_images/115957-7e7068ef5a687b48.png?imageMogr2/auto-orient/strip|imageView2/2/w/761/format/webp)
---

> #### 4.总结
- sqlite数据库文件会存放在/data/data/<package name>/databases/目录下
- getReadableDatabase和getWritableDatabase:。都可创建或打开现有数据库
- onCreate: 数据库第1次创建时调用，这里写数据初始化语句。
- onUpgrade： 数据库版本升级的时候会调用， 在这里根据传进来的oldVersion 版本号来对本地数据库结构升级。
- sqlite 支持sql 语句，尽量使用sql语句来增删改查，比较方便，直观，简单。

> #### 5. 完整demo地址
- https://github.com/domain9065/android-study-demo/tree/master/sqlite
> #### 6. 参考文章
- https://www.jianshu.com/p/8e3f294e2828
- https://www.runoob.com/sqlite/sqlite-syntax.html
> #### 6. 历史文章目录
- https://juejin.im/post/5dbadf9f6fb9a0204e658da8

