package com.example.lxview.lxhome.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.database.DBHelper
import com.example.lxview.databinding.ActivitySqlliteLayoutBinding
import com.example.lxview.lxhome.adapter.SqliteAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * author: 李 祥
 * date:   2022/4/27 4:11 下午
 * description:
 */

class SQLiteActivity : BaseBindActivity<ActivitySqlliteLayoutBinding>(){
    /** Called when the activity is first created.  */ //声明各个按钮
    private var createBtn: Button? = null
    private var insertBtn: Button? = null
    private var updateBtn: Button? = null
    private var queryBtn: Button? = null
    private var deleteBtn: Button? = null
    private var modifyBtn: Button? = null
    private var recyclerView: RecyclerView? = null

    override val layout: Int
        get() = R.layout.activity_sqllite_layout

    //通过findViewById获得Button对象的方法
    override fun initView() {
        createBtn = findViewById<View>(R.id.createDatabase) as Button
        updateBtn = findViewById<View>(R.id.updateDatabase) as Button
        insertBtn = findViewById<View>(R.id.insert) as Button
        modifyBtn = findViewById<View>(R.id.update) as Button
        queryBtn = findViewById<View>(R.id.query) as Button
        deleteBtn = findViewById<View>(R.id.delete) as Button
        recyclerView = findViewById<View>(R.id.sql_rv) as RecyclerView
    }

    override fun initListener() {
        createBtn?.setOnClickListener(CreateListener())
        updateBtn?.setOnClickListener(UpdateListener())
        insertBtn?.setOnClickListener(InsertListener())
        modifyBtn?.setOnClickListener(ModifyListener())
        queryBtn?.setOnClickListener(QueryListener())
        deleteBtn?.setOnClickListener(DeleteListener())
    }

    //创建数据库的方法
    internal inner class CreateListener : View.OnClickListener {
        override fun onClick(v: View) { //创建StuDBHelper对象
            val dbHelper = DBHelper(this@SQLiteActivity, "stu_db", null, 3) //得到一个可读的SQLiteDatabase对象
            val db: SQLiteDatabase = dbHelper.readableDatabase
        }
    }

    //更新数据库的方法
    internal inner class UpdateListener : View.OnClickListener {
        @SuppressLint("Range", "Recycle")
        override fun onClick(v: View) { // 数据库版本的更新,由原来的1变为2
            val dbHelper = DBHelper(this@SQLiteActivity, "stu_db", null, 3)
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val list: ArrayList<Map<String, Any>> = ArrayList()
            val sql = "select * from stu_table"
            val cursor:Cursor = db.rawQuery(sql,null)
            cursor.moveToFirst();

            while (!cursor.isAfterLast) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("sname"))
                val age = cursor.getInt(cursor.getColumnIndex("sage"))
                val sex = cursor.getString(cursor.getColumnIndex("ssex"))
                val map: MutableMap<String, Any> = HashMap()
                map["id"]=id
                map["sname"] = name
                map["sage"] = age
                map["ssex"] = sex
                list.add(map)
                cursor.moveToNext()
            }
            val adapter = SqliteAdapter(this@SQLiteActivity, list)
            recyclerView?.layoutManager = LinearLayoutManager(this@SQLiteActivity)
            recyclerView?.adapter = adapter
        }
    }

    //插入数据的方法
    internal inner class InsertListener : View.OnClickListener {
        override fun onClick(v: View) {
            val dbHelper = DBHelper(this@SQLiteActivity, "stu_db", null, 3) //得到一个可写的数据库
            val db: SQLiteDatabase = dbHelper.writableDatabase

            //生成ContentValues对象 //key:列名，value:想插入的值
            val cv = ContentValues() //往ContentValues对象存放数据，键-值对模式
            cv.put("id", 3)
            cv.put("sname", "xiaoming")
            cv.put("sage", 21)
            cv.put("ssex", "male") //调用insert方法，将数据插入数据库
            db.insert("stu_table", null, cv) //关闭数据库
            db.close()
        }
    }

    //查询数据的方法
    internal inner class QueryListener : View.OnClickListener {
        @SuppressLint("Range", "Recycle")
        override fun onClick(v: View) {
            val dbHelper = DBHelper(this@SQLiteActivity, "stu_db", null, 3) //得到一个可写的数据库
            val db: SQLiteDatabase = dbHelper.readableDatabase //参数1：表名
            //参数2：要想显示的列
            //参数3：where子句
            //参数4：where子句对应的条件值
            //参数5：分组方式
            //参数6：having条件
            //参数7：排序方式
            val cursor = db.query("stu_table", arrayOf("id", "sname", "sage", "ssex"), "id=?", arrayOf("3"), null, null, null)
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex("sname"))
                val age = cursor.getString(cursor.getColumnIndex("sage"))
                val sex = cursor.getString(cursor.getColumnIndex("ssex"))
                println("query------->姓名：$name 年龄：$age 性别：$sex")
            } //关闭数据库
            db.close()
        }
    }

    //修改数据的方法
    internal inner class ModifyListener : View.OnClickListener {
        override fun onClick(v: View) {
            val dbHelper = DBHelper(this@SQLiteActivity, "stu_db", null, 3) //得到一个可写的数据库
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val cv = ContentValues()
            cv.put("sage", "23") //where 子句 "?"是占位符号，对应后面的"3",
            val whereClause = "id=?"
            val whereArgs = arrayOf(3.toString()) //参数1 是要更新的表名
            //参数2 是一个ContentValues对象
            //参数3 是where子句
            db.update("stu_table", cv, whereClause, whereArgs)
        }
    }

    //删除数据的方法
    internal inner class DeleteListener : View.OnClickListener {
        override fun onClick(v: View) {
            val dbHelper = DBHelper(this@SQLiteActivity, "stu_db", null, 3) //得到一个可写的数据库
            val db: SQLiteDatabase = dbHelper.readableDatabase
            val whereClauses = "id=?"
            val whereArgs = arrayOf(2.toString()) //调用delete方法，删除数据
            db.delete("stu_table", whereClauses, whereArgs)
        }
    }
}