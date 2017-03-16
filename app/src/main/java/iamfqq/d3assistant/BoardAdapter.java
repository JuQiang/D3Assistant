package iamfqq.d3assistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JUQIANG-PC on 2017/3/16.
 */

public class BoardAdapter extends BaseAdapter {
    private List<A> list;
    private Context context;

    public BoardAdapter(Context context, List<A> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int index = position;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.customized_board_item, null);
            viewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout01);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.ItemText);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cbCheckBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.get(index).type = A.TYPE_CHECKED;
                } else {
                    list.get(index).type = A.TYPE_NOCHECKED;
                }
            }
        });

        viewHolder.textView.setText(list.get(position).board.BattleTag);
        if (list.get(position).type == A.TYPE_CHECKED) {
            viewHolder.checkBox.setChecked(true);
        } else {
            viewHolder.checkBox.setChecked(false);
        }
        return convertView;
    }
}

