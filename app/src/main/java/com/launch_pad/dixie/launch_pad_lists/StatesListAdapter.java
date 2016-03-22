package com.launch_pad.dixie.launch_pad_lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bradc on 3/22/16.
 */
public class StatesListAdapter extends BaseAdapter
{
	private ArrayList<String> mRows = null;
	private Context mContext = null;

	public StatesListAdapter(Context context, ArrayList<String> data)
	{
		super();

		mContext = context;
		mRows = data;
	}


	@Override
	public int getCount()
	{
		int result = 0;

		if (mRows != null)
		{
			result = mRows.size();
		}

		return result;
	}

	@Override
	public Object getItem(int position)
	{
		Object result = null;

		if (mRows != null && mRows.size() > 0 && position < mRows.size())
		{
			result = mRows.get(position);
		}

		return result;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;

		// Define the cell
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.state_list_item, null);

			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.state_name);
			holder.flag = (ImageView) convertView.findViewById(R.id.state_flag);

			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		// Populate values in the cell
		String stateName = mRows.get(position);

		holder.name.setText(stateName);

		String flagName = stateName.toLowerCase() + "_flag";
		flagName = flagName.replaceAll(" ", "_");

		int resId = mContext.getResources().getIdentifier(flagName, "drawable", mContext.getPackageName());
		holder.flag.setBackgroundResource(resId);

		return convertView;
	}

	private static class ViewHolder
	{
		public TextView name;
		public ImageView flag;
	}
}
