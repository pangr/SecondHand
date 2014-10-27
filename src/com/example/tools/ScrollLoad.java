package com.example.tools;

import java.util.ArrayList;

import com.example.detail.Goods_Detail;
import com.example.second_hand.MainActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class ScrollLoad {
	
	private int firstVisibleItem;
	private int visibleItemcount;
	private boolean isFirstEnter;

	
	
     public ScrollLoad(final ListView listView,final ArrayList<String> urls,final Context ctx) {
		// TODO Auto-generated constructor stub
    	 isFirstEnter = true;
    	 listView.setOnScrollListener(new OnScrollListener() {
			
    		 
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
				
					showImage(firstVisibleItem, visibleItemcount, urls,listView,ctx);
				}
			}
			
			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				firstVisibleItem = arg1;
				visibleItemcount = arg2;
				if (isFirstEnter && arg3 >0) {
					showImage(firstVisibleItem, visibleItemcount, urls,listView,ctx);
					isFirstEnter = false;
				}
			}
		});
	}
     private void  showImage(int firstVisibleItem, int visibleItemCount,ArrayList<String> urls,ListView listView,Context ctx) {
    	 for (int i = firstVisibleItem; i < firstVisibleItem+visibleItemCount-2; i++) {
    		 String mImageUrl = urls.get(i);
 			final ImageView mImageView = (ImageView) listView
 					.findViewWithTag(mImageUrl);
 			 for (int j = 0; j < urls.size(); j++) {
					Log.v("Í¼Æ¬¼¯ºÏ", urls.get(j));
				}
 			 
 			Goods_Detail.IMAGE_CACHE.get(urls.get(i), mImageView);
 			/*try {
				if (urls.get(0) != null && urls.size() > 1 ) {
					Log.v("Í¼Æ¬",urls.get(0)+"%%%%%%%");
					   //Picasso.with(ctx).load(urls.get(0)).into(mImageView);
					Goods_Detail.IMAGE_CACHE.get(urls.get(0), mImageView);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
		}
		
	}
   
}
  
