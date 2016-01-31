package com.qianfeng.gameassistant.other.widget;

import com.qianfeng.gameassistant.other.utils.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class LoadMoreListView extends ListView
{
	private float lastY;

	private LoadMoreListener loadMoreListener;

	public LoadMoreListView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		initScrollListener();

	}
	
	public LoadMoreListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initScrollListener();
	}




	private void initScrollListener()
	{

		setOnScrollListener(new OnScrollListener()
		{
			@Override
			public void onScrollStateChanged(AbsListView parentView, int scrollState)
			{
				// TODO Auto-generated method stub
				if (scrollState != SCROLL_STATE_IDLE)
				{
					return;
				}
				int count = parentView.getCount();

				int lastVisiblePosition = parentView.getLastVisiblePosition();

				int childCount = parentView.getChildCount();

				// 如果当前可见的最后一项的position等于最后一个item的position,表示显示的item是最后一项
				if (lastVisiblePosition == count - 1)
				{
					View lastChild = parentView.getChildAt(childCount - 1);

					float y = lastChild.getY();

					LogUtil.e("tag", "当前显示的item y = " + y);
					// 如果第一次随意停下来的位置和第二次随意停下来的位置相同,表示已经滑到底了
					if (lastY == y)
					{
						LogUtil.e("tag", "滑到底了 y = " + y);

						if (loadMoreListener != null)
						{
							loadMoreListener.onLoadMore();
						}

					} else
					{
						lastY = y;
					}

				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				// TODO Auto-generated method stub

			}

		});
	}

	/**
	 * 设置一个加载更多的监听
	 * 
	 * @param listener
	 *            加载更多的监听
	 */
	public void setLoadMoreListener(LoadMoreListener listener)
	{
		loadMoreListener = listener;
	}

	/**
	 * 加载更多的监听器
	 * 
	 * @author liyiwei
	 *
	 * @date:Jan 15, 2016
	 */
	public interface LoadMoreListener
	{
		/**
		 * 加载更多回调
		 */
		void onLoadMore();
	}
}
