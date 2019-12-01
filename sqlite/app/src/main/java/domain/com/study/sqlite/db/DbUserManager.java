package domain.com.study.sqlite.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import domain.com.study.sqlite.entity.User;

/**
 * 用户表操作
 */
public class DbUserManager {
    /**
     * 当前类唯一示例
     */
    private static DbUserManager instance = null;
    /**
     * SQLiteHelper 实例
     */
    private static SQLiteHelper sqLiteHelper = null;
    /**
     * 数据库操作实例
     */
//    private static SQLiteDatabase db =null;
    /**
     * 用户表  表名
     */
    public static final String TB_NAME = "user";


    public static DbUserManager getInstance(Context context){
        if (instance == null) {
            synchronized (DbUserManager.class){
                if (instance == null) {
                    instance = new DbUserManager();
                    sqLiteHelper = new SQLiteHelper(context);
//                    db = sqLiteHelper.getWritableDatabase();
                }
            }
        }
        return instance;
    }
    private DbUserManager(){}


    public void insertUser(User user) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", user.getName());
//        contentValues.put("age", user.getAge());
//        db.insert(TB_NAME, null, contentValues);


        //使用sql语句增加
//        String version1Sql = String.format("insert into user (age,name) values (%d,'%s')",user.getAge(),user.getName());
        String version2Sql = String.format("insert into user (age,name,sex) values (%d,'%s',%d)",user.getAge(),user.getName(),1);

        String version3Sql="insert into orders (orderNo) values('12341313dsafdsa')";
        db.execSQL(version3Sql);

        db.execSQL(version2Sql);
        db.close();
    }

    public void updateByName(User user, String name) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", user.getName());
//        contentValues.put("age", user.getAge());
//        db.update(TB_NAME, contentValues, "name = ?", new String[]{name});

        //使用sql语句更改
        String sql = String.format("update user set age=%d , name='%s' where name='%s'",user.getAge(),user.getName(),name);
        db.execSQL(sql);
        db.close();
    }

    public void delByName(String name) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
//        db.delete(TB_NAME, "name = ?", new String[]{name});

        //使用sql语句删除
        String sql = String.format("delete from user where name='%s'",name);
        db.execSQL(sql);
        db.close();
    }


    public List<User> queryByName(String name) {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
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
        db.close();
        return orderList;
    }



}
