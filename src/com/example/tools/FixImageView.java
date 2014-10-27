package com.example.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class FixImageView extends ImageView {

	public FixImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    @Override
    public void setImageBitmap(Bitmap bm) {
    	// TODO Auto-generated method stub
    	super.setImageBitmap(bm);
    }
  
}
