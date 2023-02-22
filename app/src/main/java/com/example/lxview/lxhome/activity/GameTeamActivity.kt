package com.example.lxview.lxhome.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.database.DBHelper
import com.example.lxview.databinding.ActivityGameTeamLayoutBinding
import com.example.lxview.lxhome.adapter.GameTeamAdapter
import com.example.lxview.lxhome.adapter.SqliteAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * author: 李 祥
 * date:   2022/4/27 4:11 下午
 * description:游戏分组
 */

class GameTeamActivity : BaseBindActivity<ActivityGameTeamLayoutBinding>() {
    /** Called when the activity is first created.  */ //声明各个按钮
    private val insertPlayerInfoEt: AppCompatEditText? by lazy { mBinding.insertPlayerInfoEt }
    private val deletePlayerInfoEt: AppCompatEditText? by lazy { mBinding.deletePlayerInfoEt }
    private val insertPlayerInfoTv: AppCompatTextView? by lazy { mBinding.insertPlayerInfoTv }
    private val deletePlayerInfoTv: AppCompatTextView? by lazy { mBinding.deletePlayerInfoTv }
    private val starTeamBt: Button by lazy { mBinding.starTeamBt }
    private val deleteAllPlayersInfo: Button by lazy { mBinding.deleteAllPlayersInfo }
    private val queryPlayersInfo: Button by lazy { mBinding.queryPlayersInfo }

    private val joinGamePlayerRv: RecyclerView by lazy { mBinding.joinGamePlayerRv }
    private val teamPlayerRv: RecyclerView by lazy { mBinding.teamPlayerRv }
    var teamList: ArrayList<Map<String, Any>> = ArrayList()


    override val layout: Int
        get() = R.layout.activity_game_team_layout

    //通过findViewById获得Button对象的方法
    override fun initView() {

    }

    override fun initListener() {
        insertPlayerInfoTv?.setOnClickListener { insertPlayerListener() }
        deletePlayerInfoTv?.setOnClickListener { deletePlayerListener() }
        starTeamBt.setOnClickListener { startTeamPlayGame() }
        queryPlayersInfo.setOnClickListener { updatePlayerListener() }
        deleteAllPlayersInfo.setOnClickListener { }
    }

    private fun startTeamPlayGame() {
        updatePlayerListener()
        val adapter = GameTeamAdapter(this@GameTeamActivity, teamList)
        teamPlayerRv.layoutManager = GridLayoutManager(this@GameTeamActivity, 2)
        teamPlayerRv.adapter = adapter

    }

    //创建数据库的方法
    internal inner class CreateListener : View.OnClickListener {
        override fun onClick(v: View) { //创建StuDBHelper对象
            val dbHelper = DBHelper(this@GameTeamActivity, "player_db", null, 3) //得到一个可读的SQLiteDatabase对象
            val db: SQLiteDatabase = dbHelper.readableDatabase
        }
    }

    //更新数据库的方法
    @SuppressLint("Range", "Recycle")
    private fun updatePlayerListener() {
        val dbHelper = DBHelper(this@GameTeamActivity, "player_db", null, 3)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val list: ArrayList<Map<String, Any>> = ArrayList()
        val sql = "select * from player_table"
        val cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst();

        while (!cursor.isAfterLast) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("sname")) ?: "unknown"
            val age = cursor.getInt(cursor.getColumnIndex("sage"))
            val sex = cursor.getString(cursor.getColumnIndex("ssex"))
            val map: MutableMap<String, Any> = HashMap()
            map["id"] = id
            map["sname"] = name
            map["sage"] = age
            map["ssex"] = sex
            list.add(map)
            teamList = list
            cursor.moveToNext()
        }
        val adapter = SqliteAdapter(this@GameTeamActivity, list)
        joinGamePlayerRv.layoutManager = LinearLayoutManager(this@GameTeamActivity)
        joinGamePlayerRv.adapter = adapter
        adapter.setVisibility(false)
    }

    //插入数据的方法
    private fun insertPlayerListener() {
        val dbHelper = DBHelper(this@GameTeamActivity, "player_db", null, 3) //得到一个可写的数据库
        val db: SQLiteDatabase = dbHelper.writableDatabase //生成ContentValues对象 //key:列名，value:想插入的值
        val cv = ContentValues() //往ContentValues对象存放数据，键-值对模式
        var playerName: String?
        if (insertPlayerInfoEt?.text.toString().isNotEmpty()) {
            playerName = insertPlayerInfoEt?.text.toString().trim()
            cv.put("id", 3)
            cv.put("sage", 21)
            cv.put("ssex", "男") //调用insert方法，将数据插入数据库
            cv.put("sname", playerName)
            db.insert("player_table", null, cv) //关闭数据库
            db.close()
            ToastUtils.showToast(this@GameTeamActivity, "录入成功", color = R.color.login_click_tv_color_ef7300)
        } else ToastUtils.showToast(this@GameTeamActivity, "输入框不能为空", color = R.color.login_tips_e03a42)

    }

    //查询数据的方法
    @SuppressLint("Range", "Recycle")
    fun queryListener() {
        val dbHelper = DBHelper(this@GameTeamActivity, "player_db", null, 3) //得到一个可写的数据库
        val db: SQLiteDatabase = dbHelper.readableDatabase //参数1：表名 //参数2：要想显示的列 //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        val cursor = db.query("player_table", arrayOf("id", "sname", "sage", "ssex"), "id=?", arrayOf("3"), null, null, null)
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("sname"))
            val age = cursor.getString(cursor.getColumnIndex("sage"))
            val sex = cursor.getString(cursor.getColumnIndex("ssex"))
            println("query------->姓名：$name 年龄：$age 性别：$sex")
        } //关闭数据库
        db.close()

    }

    //修改数据的方法
    private fun modifyListener() {
        val dbHelper = DBHelper(this@GameTeamActivity, "player_db", null, 3) //得到一个可写的数据库
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val cv = ContentValues()
        cv.put("sage", "23") //where 子句 "?"是占位符号，对应后面的"3",
        val whereClause = "id=?"
        val whereArgs = arrayOf(3.toString()) //参数1 是要更新的表名 //参数2 是一个ContentValues对象 //参数3 是where子句
        db.update("player_table", cv, whereClause, whereArgs)
    }

    //删除数据的方法
    private fun deletePlayerListener() {
        val dbHelper = DBHelper(this@GameTeamActivity, "player_db", null, 3) //得到一个可写的数据库
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val whereClauses = "id=?"
        val whereArgs = arrayOf(3.toString()) //调用delete方法，删除数据
        db.delete("player_table", whereClauses, whereArgs)
    }
}