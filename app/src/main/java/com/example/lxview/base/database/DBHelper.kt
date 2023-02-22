package com.example.lxview.base.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * 必须要有构造函数
 */
class DBHelper(context: Context?, name: String?, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    // 当第一次创建数据库的时候，调用该方法
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table stu_table(id int,sname varchar(20),sage int,ssex varchar(10))" //输出创建数据库的日志信息
        val sql2 = "create table player_table(id int,sname varchar(20),sage int,ssex varchar(10))" //输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->") //execSQL函数用于执行SQL语句
        db.execSQL(sql)
        db.execSQL(sql2)
    }

    //当更新数据库的时候执行该方法
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { //输出更新数据库的日志信息
        Log.i(TAG, "update Database------------->")
    }

    companion object {
        private const val TAG = "TestSQLite"
        const val VERSION = 1
    }
}