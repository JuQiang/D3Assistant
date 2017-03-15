package iamfqq.d3assistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JUQIANG-PC on 2017/3/15.
 */

public class CustomizedSpinnerAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;

    public CustomizedSpinnerAdapter(Context pContext,List<String> pList) {
        this.mContext = pContext;
        this.mList = pList;
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
    /**
     * 下面是重要代码
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.customized_spinner_item, null);
        if(convertView!=null)
        {
            TextView _TextView2=(TextView)convertView.findViewById(R.id.textView2);
            _TextView2.setText(mList.get(position));
        }
        return convertView;
    }
}
