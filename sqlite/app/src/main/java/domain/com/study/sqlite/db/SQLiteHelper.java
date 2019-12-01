package domain.com.study.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 1. 数据库文件会存放在/data/data/<package name>/databases/目录下
 * 2. getReadableDatabase和getWritableDatabase:。都可创建或打开现有数据库（数据库已经存在则直接打开，没有则创建）,用于读/写数据库。区别在于磁盘空间满了的时候，数据库不可写用getWritableDatabase打开就会直接报错，但getReadableDatabase打开失败后会尝试以只读方式打开。
 * 3. onCreate: 数据库第1次创建时则会调用，即第1次调用 getWritableDatabase（） / getReadableDatabase（）时调用
 * 4. onUpgrade： 数据库版本升级的时候会调用， 可以根据传进来的oldVersion 版本号来对本地数据库结构升级
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myTest.db";

    private static final int DATABASE_VERSION = 3;

    //将建立表语句定义成字符串常量
    private String createUserSql = "create table if not exists user (id integer primary key autoincrement, name text, age integer);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("new version:"+newVersion);

        switch (oldVersion){
            case 1:
            toVersion2(db);
            case 2:
            toVersion3(db);
            break;
        }

    }

    private void toVersion2(SQLiteDatabase db){
        String sql = "alter table user add sex integer";
        db.execSQL(sql);

    }

    private void toVersion3(SQLiteDatabase db){
        String sql = "create table if not exists orders(id integer primary key autoincrement, orderNo varch(32))";
        db.execSQL(sql);
    }



}
