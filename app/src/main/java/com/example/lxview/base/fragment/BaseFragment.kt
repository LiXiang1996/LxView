package com.example.lxview.base.fragment

/**
 * author: 李 祥
 * date:   2021/12/10 11:54 上午
 * description:
 */
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

@SuppressLint("MissingSuperCall")
abstract class BaseFragment : Fragment() {

    /**
     * content resource id
     */
    protected abstract val contentId: Int


    /**
     * content view
     */
    lateinit var mRootView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
        initListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(contentId, container, false)
        return mRootView
    }

    override fun onResume() {
        super.onResume()
    }

    open fun initView() {}
    open fun initData() {}
    open fun initListener() {}
}