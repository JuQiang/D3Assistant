package iamfqq.d3assistant;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JuQiang on 2/22/2017.
 */

public class CareerImageAdapter extends BaseAdapter {
    private Context context;
    private CareerProfile careerProfile;
    private Map<Integer, ProfileView> viewCache = new HashMap<Integer, ProfileView>();

    public CareerImageAdapter(Context context, CareerProfile careerProfile) {
        this.context = context;
        this.careerProfile = careerProfile;

        for (int i = 0; i < careerProfile.heroes.size(); i++) {
            ProfileView pv = new ProfileView(this.context, careerProfile.heroes.get(i));
            pv.setPadding(8, 8, 8, 8);//每个图像之间的间距

            viewCache.put(i, pv);
        }
    }

    @Override
    public int getCount() {
        int count = this.careerProfile.heroes.size();
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int heroIndex, View heroView, ViewGroup viewGroup) {

        ProfileView pv = (ProfileView) (viewCache.get(heroIndex));
        /*
        //这种方式会卡，所以在构造函数里面一口气搞出来算了。
        if (pv == null) {
            pv = new ProfileView(this.context,careerProfile.getHeroes().get(heroIndex));
            pv.setPadding(8, 8, 8, 8);//每个图像之间的间距

            viewCache.put(heroIndex,pv);
        }
*/
        return pv;
    }
}
