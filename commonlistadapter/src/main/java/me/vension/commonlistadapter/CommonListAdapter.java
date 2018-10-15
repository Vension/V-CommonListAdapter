package me.vension.commonlistadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ========================================================
 * 作  者：Vension
 * 日  期：2018/3/30 16:50
 * 描  述：ListView 和 GridView 的通用适配器
 * ========================================================
 */

public abstract class CommonListAdapter<T> extends BaseAdapter {

	protected Context mContext;//上下文
	protected List<T> listDatas = new ArrayList<>();//存放数据集合
	protected int mLayoutId;//item布局

	public CommonListAdapter(Context context,int layoutId,List<T> data){
		this.mContext = context;
		this.mLayoutId = layoutId;
		this.listDatas = data;
	}

	public CommonListAdapter(Context context, int layoutId){
		this.mContext = context;
		this.mLayoutId = layoutId;
	}

	@Override
	public int getCount() {
		return listDatas.size();
	}

	@Override
	public T getItem(int position) {
		return listDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 添加单条数据项
	 * @param item
	 * @param addHead 是否添加在头部
	 */
	public void addItem(boolean addHead,T item){
		if (addHead){
			this.listDatas.add(0,item);
		}else{
			this.listDatas.add(item);
		}
		refresh();
	}

	/**
	 * 移除单条数据项
	 * @param position
	 */
	public void removeItem(int position){
		if (this.listDatas.size() > 0){
			this.listDatas.remove(position);
			refresh();
		}
	}

	/**
	 * 添加多条数据项
	 * @param lists
	 * @param addHead 是否添加在头部
	 */
	public void addData(boolean addHead,List<T> lists) {
        this.listDatas.addAll(addHead ? 0 : this.listDatas.size(),lists);
		refresh();
	}

	/**
	 * 设置数据源
	 * @param data
	 */
	public void setListDatas(List<T> data){
		listDatas.clear();
		listDatas.addAll(data);
		refresh();
	}

	/**
	 * 清除数据源
	 */
	public void clear(){
		this.listDatas.clear();
	}

	/**
	 * 获取数据源
	 */
	public List<T> getDatas() {
		return listDatas;
	}

	/**
	 * 刷新数据源
	 */
	public void refresh(){
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CommonListViewHolder holder = CommonListViewHolder.get(mContext, convertView, parent, mLayoutId);
		convert(holder, position, listDatas.get(position));
		return holder.getConvertView();
	}

	/**
	 * 在子类中实现该方法
	 * @param holder 列表项
	 * @param item
	 */
	public abstract void convert(CommonListViewHolder holder, int position, T item);

}