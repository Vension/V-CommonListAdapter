package me.vension.commonlistadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.*

/**
 * ========================================================
 * @author: Created by Vension on 2018/9/12 11:25.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */

abstract class CommonListAdapterForKotlin<T> : BaseAdapter{

    private var mContext: Context//上下文
    private var listDatas = ArrayList<T>()//存放数据集合
    private var mLayoutId: Int = 0//item布局

    constructor(mContext: Context, mLayoutId: Int) {
        this.mContext = mContext
        this.mLayoutId = mLayoutId
    }

     constructor(mContext: Context, listDatas: ArrayList<T>, mLayoutId: Int) {
        this.mContext = mContext
        this.listDatas = listDatas
        this.mLayoutId = mLayoutId
    }

    override fun getCount(): Int {
        return listDatas.size
    }

    override fun getItem(position: Int): T {
        return listDatas[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:CommonListViewHolderForKotlin
        if (convertView == null) {
            viewHolder = CommonListViewHolderForKotlin(mContext, parent, mLayoutId)
        } else {
            viewHolder = convertView.tag as CommonListViewHolderForKotlin
        }
        convert(viewHolder, position, listDatas[position])
        return viewHolder.getConvertView()

    }


    /**
     * 在子类中实现该方法
     * @param holder 列表项
     * @param item
     */
    abstract fun convert(holder: CommonListViewHolderForKotlin, position: Int, item: T)


    /**
     * 添加单条数据项
     * @param item
     * @param addHead 是否添加在头部
     */
    fun addItem(addHead: Boolean, item: T) {
        if (addHead) {
            this.listDatas.add(0, item)
        } else {
            this.listDatas.add(item)
        }
        refresh()
    }

    /**
     * 移除单条数据项
     * @param position
     */
    fun removeItem(position: Int) {
        if (this.listDatas.size > 0) {
            this.listDatas.removeAt(position)
            refresh()
        }
    }

    /**
     * 添加多条数据项
     * @param lists
     * @param addHead 是否添加在头部
     */
    fun addData(addHead: Boolean, lists: List<T>) {
        this.listDatas.addAll(if (addHead) 0 else this.listDatas.size, lists)
        refresh()
    }

    /**
     * 设置数据源
     * @param data
     */
    fun setListDatas(data: List<T>) {
        listDatas.clear()
        listDatas.addAll(data)
        refresh()
    }

    /**
     * 清除数据源
     */
    fun clear() {
        this.listDatas.clear()
    }

    /**
     * 获取数据源
     */
    fun getDatas(): List<T> {
        return listDatas
    }

    /**
     * 刷新数据源
     */
    fun refresh() {
        this.notifyDataSetChanged()
    }


}

