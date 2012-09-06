/**
 * 
 */
package cn.salesuite.saf.view;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Tony Shen
 *
 */
public class IconSimpleAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mList;
	private String[] mKeys;
	private int mResource;
	private int[] mViews;

	public IconSimpleAdapter(Context context, List<Map<String, Object>> list, int resource, String[] keys, int[] views) {
		mInflater = LayoutInflater.from(context);
		mList = list;
		mKeys = keys;
		mResource = resource;
		mViews = views;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		
		Map<String, Object> map = mList.get(position);
		convertView = mInflater.inflate(mResource, null);
			
		for(int i=0;i<mViews.length;i++){	
			View uiElement = convertView.findViewById(mViews[i]);
			if(uiElement instanceof ImageView){
				if (map.get(mKeys[i])!=null) {
					if(map.get(mKeys[i]) instanceof Bitmap){
						((ImageView)uiElement).setImageBitmap((Bitmap)map.get(mKeys[i]));
					}else{
						int icon = Integer.parseInt(map.get(mKeys[i]).toString());
						if(icon == 0){
							((ImageView)uiElement).setVisibility(View.GONE);
						}else{
							((ImageView)uiElement).setImageResource(icon);
						}
					}
				}
			}else if(uiElement instanceof TextView){
				if (map.get(mKeys[i]) != null) {
					if(map.get(mKeys[i]).toString().length() == 0) {
						((TextView)uiElement).setVisibility(View.GONE);
					}
					((TextView)uiElement).setText(map.get(mKeys[i]).toString());
				}
			}
		}
			
		return convertView;
	}
	
	@Override  
    public void notifyDataSetChanged() {  
        super.notifyDataSetChanged();  
    }  

}