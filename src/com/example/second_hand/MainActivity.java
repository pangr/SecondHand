package com.example.second_hand;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.example.tools.GetData;
import com.example.tools.HandlrGetData;
import com.example.tools.HandlrGetData.onDataLoadListener;
import com.example.tools.JsonDeal;
import com.example.tools.KindArrayAdapter;
import com.example.tools.ScrollLoad;

import cn.trinea.android.common.view.DropDownListView;
import cn.trinea.android.common.view.DropDownListView.OnDropDownListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity {

   
    private DropDownListView listView  = null;
    KindArrayAdapter adapter;
    String result;
    public static List<String> arr ;
    private List<SecondHandItem> handItems;
    private RelativeLayout headerlayout;
	private int height;
    
    @SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handItems = new ArrayList<SecondHandItem>();
        
        ActionBar actionBar = getActionBar();
        actionBar.show();    
        actionBar.setDisplayHomeAsUpEnabled(false);     
       
        listView = (DropDownListView)findViewById(R.id.list_view);
        // set drop down listener
        headerlayout = listView.getHeaderLayout();
        height = headerlayout.getMeasuredHeight() + 100;
        listView.setOnDropDownListener(new OnDropDownListener() {

            @Override
            public void onDropDown() {
                new GetDataTask(true).execute("http://m.bistu.edu.cn/newapi/secondhand.php");
                }
        });

        // set on bottom listener
        listView.setOnBottomListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
  //              new GetDataTask(false).execute();
            }
        });
      
        new GetDataTask(true).execute("http://m.bistu.edu.cn/newapi/secondhand.php");
        HandlrGetData.data("http://m.bistu.edu.cn/newapi/secondhandtype.php", new onDataLoadListener() {
			
			@Override
			public void onDataLoadr(String result) {
				// TODO Auto-generated method stub
				arr = JsonDeal.getClasses(result);
			}
		});
        listView.setSecondPositionVisible();
    }
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	 new GetDataTask(true).execute("http://m.bistu.edu.cn/newapi/secondhand.php");
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
		
		   Intent intent =new Intent(MainActivity.this,Filter.class);
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
		 	 //setContentViewĬ�ϲ��ܵ�������̣��¾��������������ʾ��
		 	Dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		 	
			SearchView  sv=(SearchView) Dialog.getWindow().findViewById(R.id.search);
			
		        //���ø�SearchViewĬ���Ƿ��Զ���СΪͼ��
		        sv.setIconifiedByDefault(false);
		        //���ø�SearchView��ʾ������ť
		        sv.setSubmitButtonEnabled(true);
		        //���ø�SearchView��Ĭ����ʾ����ʾ�ı�
		        sv.setQueryHint("����");
		        //Ϊ��SearchView��������¼�������
		        sv.setOnQueryTextListener(new OnQueryTextListener() {
					
					@Override
					public boolean onQueryTextSubmit(String query) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "��ѡ����ǣ�"+query, Toast.LENGTH_SHORT).show();
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
				
			   final AlertDialog salert = new AlertDialog.Builder(this).create(); 
		         salert.show(); 
		        //******************�ı�alertdialogλ��
			   Window dialogWindow = salert.getWindow();
		        salert.setCanceledOnTouchOutside(true);
		        dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
		   	    WindowManager m = getWindowManager();
		        Display d = m.getDefaultDisplay(); // ��ȡ��Ļ������
		        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		        p.width = (int) (d.getWidth() * 0.4); // �������Ϊ��Ļ��0.65
		        p.y=100;
		        dialogWindow.setAttributes(p);
                
		        salert.getWindow().setContentView(R.layout.settings_items); 
		       // salert.getWindow().
		        LinearLayout submit=(LinearLayout)salert.getWindow().findViewById(R.id.submit); 
		        submit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(MainActivity.this,Sell_Goods.class);
						salert.cancel();
						startActivity(intent);
						
					}
				});
		        
		        LinearLayout person=(LinearLayout)salert.getWindow().findViewById(R.id.person); 
		        person.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(MainActivity.this,MyPublish.class);
						salert.cancel();
						startActivity(intent); 
						
					}
				});
		        
				break;
				
		}

		return super.onOptionsItemSelected(item);
	}
    
  
    public class GetDataTask extends AsyncTask<String, Integer, String> {

        private boolean isDropDown;

        public GetDataTask(boolean isDropDown){
            this.isDropDown = isDropDown;
        }

        @Override
        protected String doInBackground(String... params) {
      
    		
			try {
				result=	new GetData(params[0]).Deal();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			return result;

        }

        @Override
        protected void onPostExecute(String result) {

        	
            if (isDropDown) { 
            	 ArrayList<String> urls = new JsonDeal(result, MainActivity.this).Deal();
            	ScrollLoad scrollLoad = new ScrollLoad(listView, urls,MainActivity.this);
            	handItems = JsonDeal.getseondhanditem(result);
			    adapter=new KindArrayAdapter(handItems, MainActivity.this);
             
    	        listView.setAdapter(adapter);
    	        listView.setOnItemClickListener(new DrawerItemClickListener(handItems, MainActivity.this,0));
                adapter.notifyDataSetChanged();
               
                
               // System.out.println(scrollLoad.getVisibleCount()+"###########");
                
                // should call onDropDownComplete function of DropDownListView at end of drop down complete.
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
                listView.onDropDownComplete(getString(R.string.update_at)
                                            + dateFormat.format(new Date()));
            } else {
 
                listView.onBottomComplete();
                
            }

            super.onPostExecute(result);
        }
    }
}
