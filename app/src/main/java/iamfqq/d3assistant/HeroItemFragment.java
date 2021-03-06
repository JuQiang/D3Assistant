package iamfqq.d3assistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class HeroItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_hero_item, container, false);
        ItemView iv = (ItemView)view.findViewById(R.id.myItemView);
        iv.setHero(hero);

        return iv;
    }

    private Hero hero;

    public void SetHero(Hero hero) {
        this.hero = hero;
    }
}
