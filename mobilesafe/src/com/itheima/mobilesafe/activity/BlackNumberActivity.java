package com.itheima.mobilesafe.activity;

import java.util.ArrayList;

import com.example.mobilesafe.R;
import com.itheima.mobilesafe.classDemo.BlackNumber;
import com.itheima.mobilesafe.db.dao.BlackNumberDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BlackNumberActivity extends Activity {
	private ArrayList<BlackNumber> list;
	private BlackNumberDao dao;
	private ListView lvBlackNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blacknumber);
		lvBlackNumber = (ListView) findViewById(R.id.lv_black_number);
		dao = BlackNumberDao.getInstance(BlackNumberActivity.this);

		initDate();
	}

	private void initDate() {
		// TODO Auto-generated method stub
		list = dao.findAll();
		BlackAdapter adapter = new BlackAdapter();
		lvBlackNumber.setAdapter(adapter);

	}

	class BlackAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				view = View.inflate(BlackNumberActivity.this,
						R.layout.blacknumber_list_item, null);
				holder.tvNumber = (TextView) view
						.findViewById(R.id.tv_blacknumber);
				holder.tvMode = (TextView) view.findViewById(R.id.tv_blackmode);
				holder.ivDelate = (ImageView) view.findViewById(R.id.iv_delete);
				view.setTag(holder);
			} else {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			}
			BlackNumber blackNumber = list.get(position);
			holder.tvNumber.setText(blackNumber.getNumber());
			switch (blackNumber.getMode()) {
			case 1:
				holder.tvMode.setText("拦截电话");
				break;
			case 2:
				holder.tvMode.setText("拦截短信");
				break;
			case 3:
				holder.tvMode.setText("拦截全部");
				break;
			default:
				break;
			}

			return view;
		}

		class ViewHolder {
			TextView tvNumber;
			TextView tvMode;
			ImageView ivDelate;
		}

	}
}
