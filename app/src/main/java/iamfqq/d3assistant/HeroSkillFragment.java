package iamfqq.d3assistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class HeroSkillFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return  inflater.inflate(R.layout.tab_hero_skill, container, false);

    }

    private Hero hero;
    public void SetHero(Hero hero){
        this.hero = hero;
    }
}
