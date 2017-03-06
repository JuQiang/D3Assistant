package iamfqq.d3assistant;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import iamfqq.d3assistant.CareerProfile;
import iamfqq.d3assistant.Friend;
import iamfqq.d3assistant.ProfileView;

/**
 * Created by JUQIANG-PC on 2017/3/6.
 */

public class FriendImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Friend> friends;
    private Map<Integer, FriendView> viewCache = new HashMap<Integer, FriendView>();

    public FriendImageAdapter(Context context, ArrayList<Friend> friends) {
        this.context = context;
        this.friends = friends;

        for (int i = 0; i < friends.size();i++) {
            FriendView fv = new FriendView(this.context,i,friends.get(i));

            viewCache.put(i, fv);
        }
    }
    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int friendIndex) {
        return friends.get(friendIndex);
    }

    @Override
    public long getItemId(int friendIndex) {
        return friendIndex;
    }

    @Override
    public View getView(int friendIndex, View friendView, ViewGroup parent) {
        return (FriendView) (viewCache.get(friendIndex));
    }
}
