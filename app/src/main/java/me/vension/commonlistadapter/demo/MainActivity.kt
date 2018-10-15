package me.vension.commonlistadapter.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.vension.commonlistadapter.CommonListAdapterForKotlin
import me.vension.commonlistadapter.CommonListViewHolderForKotlin
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mAdapter: CommonListAdapterForKotlin<TestBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

    private fun initData() {
        btn_refresh_data.setOnClickListener(this)
        btn_add_data.setOnClickListener(this)
        btn_add_moredata.setOnClickListener(this)
        btn_remove_data.setOnClickListener(this)
        btn_clear_data.setOnClickListener(this)
        val mList = ArrayList<TestBean>()
        for (i in 0..5) {
            val mBean = TestBean( R.drawable.icon_list,"我是标题$i", "我是简介$i")
            mList.add(mBean)
        }
        mAdapter = object :CommonListAdapterForKotlin<TestBean>(this,R.layout.item_list,mList){
            override fun convert(holder: CommonListViewHolderForKotlin, position: Int, item: TestBean) {
                holder.setImageResource(R.id.iv_image,item.imageRes)
                holder.setText(R.id.tv_title,item.title)
                holder.setText(R.id.tv_desc,item.desc)
            }
        }

        listview.adapter = mAdapter
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_refresh_data ->{
                //刷新数据
                refreshData()
            }
            R.id.btn_add_data ->{
                //新增一条数据
                addData()
            }
            R.id.btn_add_moredata ->{
                //新增多条数据
                addMoreData()
            }
            R.id.btn_remove_data ->{
                //移除一条数据
                removeData()
            }
            R.id.btn_clear_data ->{
                //清空数据
                clearData()
            }
        }
    }

    private fun clearData() {
        mAdapter?.clear()
        mAdapter?.refresh()
    }

    private fun removeData() {
        mAdapter?.removeItem(0)
    }

    private fun addData() {
        val mBean = TestBean( R.drawable.icon_list,"我是标题-新增一条", "我是简介-新增一条")
        mAdapter?.addItem(true,mBean)
    }

    private fun addMoreData() {
        val mList = ArrayList<TestBean>()
        for (i in 0..3) {
            val mBean = TestBean( R.drawable.icon_list,"我是标题-新增多条$i", "我是简介-新增多条$i")
            mList.add(mBean)
        }
        mAdapter?.addData(true,mList)
    }

    private fun refreshData() {
        val mList = ArrayList<TestBean>()
        for (i in 0..5) {
            val mBean = TestBean( R.drawable.icon_list,"我是标题-刷新$i", "我是简介-刷新$i")
            mList.add(mBean)
        }
        mAdapter?.setListDatas(mList)
    }

}
