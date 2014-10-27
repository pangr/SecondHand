package com.example.second_hand;

import java.util.ArrayList;
import java.util.List;

import com.example.tools.HandlrGetData;
import com.example.tools.HcDownRefreshList.IReflashListener;
import com.example.tools.JsonDeal;
import com.example.tools.KindArrayAdapter;
import com.example.tools.HandlrGetData.onDataLoadListener;
import com.example.tools.HcDownRefreshList;
import com.example.tools.MoreAdapter;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

public class SecondHand extends ActionBarActivity {
	
	private HcDownRefreshList list;
	public static List<String> arr ;
	private KindArrayAdapter adapter;
	private List<SecondHandItem> handItems;
	private PopupWindow popupWindow;
	private int screenWidth;
	
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondhand);
		
		ActionBar actionBar = getActionBar();
		actionBar.show();
		actionBar.setDisplayHomeAsUpEnabled(false);
		
		handItems = new ArrayList<SecondHandItem>();
		list = (HcDownRefreshList)findViewById(R.id.good_list);
		
		 list.setInterface(new IReflashListener() {
				
				@Override
				public void onReflash() {
					// TODO Auto-generated method stub
					  Handler handler = new Handler();
				        handler.postDelayed(new Runnable() {
				            @Override
				            public void run() {
				                getdata();
				                adapter.notifyDataSetChanged();
				                list.reflashComplete();
				            }
				        }, 3000);
				}
			});
		 DisplayMetrics dm = new DisplayMetrics();
	     getWindowManager().getDefaultDisplay().getMetrics(dm);
		 screenWidth = dm.widthPixels;
		 
		 HandlrGetData.data("http://m.bistu.edu.cn/newapi/secondhandtype.php", new onDataLoadListener() {
				
				@Override
				public void onDataLoadr(String result) {
					// TODO Auto-generated method stub
					arr = JsonDeal.getClasses(result);
				}
			});
		
		//getdata();
		
	}
	 @Override
	    protected void onResume() {
	    	// TODO Auto-generated method stub
	    	super.onResume();
	    	getdata();
	    }
	 

	   
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	getMenuInflater().inflate(R.menu.main, menu);
	    	MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS); 
	    	MenuItemCompat.setShowAsAction(menu.getItem(1), MenuItemCompat.SHOW_AS_ACTION_ALWAYS); 
	    	//MenuItemCompat.setShowAsAction(menu.getItem(2), MenuItemCompat.SHOW_AS_ACTION_ALWAYS); 
	    	    
	    	    return super.onCreateOptionsMenu(menu);
	    }
		 @Override
			public boolean onOptionsItemSelected(MenuItem item) {

				switch (item.getItemId()) {
				case R.id.place:
				
				   Intent intent =new Intent(SecondHand.this,Filter.class);
				   startActivity(intent);
				   
		  
				 	 break;
		/*		case R.id.menu_search:
				 
					 AlertDialog Dialog = new AlertDialog.Builder(MainActivity.this).create(); 
					 
					 Dialog.show(); 
					 Dialog.getWindow().setGravity(Gravity.TOP); 
					 Dialog.getWindow().setLayout( 
					 android.view.WindowManager.LayoutParams.FILL_PARENT, 
					 android.view.WindowManager.LayoutParams.WRAP_CONTENT); 
				 	 Dialog.getWindow().setContentView(R.layout.top);
				 	 //setContentView默认不能弹出软键盘，下句是设置软键盘显示的
				 	Dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
				 	
					SearchView  sv=(SearchView) Dialog.getWindow().findViewById(R.id.search);
					
				        //设置该SearchView默认是否自动缩小为图标
				        sv.setIconifiedByDefault(false);
				        //设置该SearchView显示搜索按钮
				        sv.setSubmitButtonEnabled(true);
				        //设置该SearchView内默认显示的提示文本
				        sv.setQueryHint("查找");
				        //为该SearchView组件设置事件监听器
				        sv.setOnQueryTextListener(new OnQueryTextListener() {
							
							@Override
							public boolean onQueryTextSubmit(String query) {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(), "您选择的是："+query, Toast.LENGTH_SHORT).show();
								return false;
							}
							
							@Override
							public boolean onQueryTextChange(String newText) {
								// TODO Auto-generated method stub
								return true;
							}
						});
				       
				        break;*/
				case R.id.action_settings:
						showPopWindow();
					  /* final AlertDialog salert = new AlertDialog.Builder(this).create(); 
				         salert.show(); 
				        //******************改变alertdialog位置
					   Window dialogWindow = salert.getWindow();
				        salert.setCanceledOnTouchOutside(true);
				        dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
				   	    WindowManager m = getWindowManager();
				        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
				        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
				        p.width = (int) (d.getWidth() * 0.4); // 宽度设置为屏幕的0.65
				        p.y=100;
				        dialogWindow.setAttributes(p);
		                
				        salert.getWindow().setContentView(R.layout.settings_items); 
				       // salert.getWindow().
				        LinearLayout submit=(LinearLayout)salert.getWindow().findViewById(R.id.submit); 
				        submit.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(SecondHand.this,Sell_Goods.class);
								salert.cancel();
								startActivity(intent);
								
							}
						});
				        
				        LinearLayout person=(LinearLayout)salert.getWindow().findViewById(R.id.person); 
				        person.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(SecondHand.this,MyPublish.class);
								salert.cancel();
								startActivity(intent); 
								
							}
						});
				        */
						break;
						
				}

				return super.onOptionsItemSelected(item);
			}
		 private void getdata(){
			 HandlrGetData.data("http://m.bistu.edu.cn/newapi/secondhand.php", new onDataLoadListener() {
				
				@Override
				public void onDataLoadr(String result) {
					// TODO Auto-generated method stub
					handItems = JsonDeal.getseondhanditem(result);
					ArrayList<String> urls = new JsonDeal(result, SecondHand.this).Deal();
					adapter=new KindArrayAdapter(handItems, SecondHand.this);
					//new ScrollLoad(list, urls, SecondHand.this);
					list.setOnItemClickListener(new DrawerItemClickListener(handItems, SecondHand.this, 0));
					list.setAdapter(adapter);
				   
				}
			});
		 }
		 private void showPopWindow(){
			 if (popupWindow == null) {
				LayoutInflater inflater = LayoutInflater.from(SecondHand.this);
				View view = inflater.inflate(R.layout.popwindow, null);
				ListView listView = (ListView) view.findViewById(R.id.moreList);
				List<String> list = new ArrayList<String>();
				list.add("发布");
				list.add("我的发布");
				MoreAdapter adapter = new MoreAdapter(list, SecondHand.this);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						switch (arg2) {
						case 0:
							Intent intent=new Intent(SecondHand.this,Sell_Goods.class);
							popupWindow.dismiss();
							startActivity(intent);
							break;
						case 1:
							Intent intent2=new Intent(SecondHand.this,MyPublish.class);
							popupWindow.dismiss();;
							startActivity(intent2); 
							break;
						default:
							break;
						}
					}
				});
				 popupWindow = new PopupWindow(view, screenWidth/2, LayoutParams.WRAP_CONTENT);
			}
			    popupWindow.setFocusable(true);
				popupWindow.setOutsideTouchable(true);
				popupWindow.setBackgroundDrawable(new BitmapDrawable());
				popupWindow.showAsDropDown(findViewById(R.id.action_settings), 0, 0);
		 }
}
