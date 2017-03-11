package iamfqq.d3assistant;

import android.graphics.BitmapFactory;
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
        View ret = inflater.inflate(R.layout.tab_hero_skill, container, false);

        ((ActiveSkillView)ret.findViewById(R.id.skill1)).Skill = this.hero.ActiveSkillList.get(0);
        ((ActiveSkillView)ret.findViewById(R.id.skillR)).Skill = this.hero.ActiveSkillList.get(1);
        ((ActiveSkillView)ret.findViewById(R.id.skill1)).Skill = this.hero.ActiveSkillList.get(2);
        ((ActiveSkillView)ret.findViewById(R.id.skill2)).Skill = this.hero.ActiveSkillList.get(3);
        ((ActiveSkillView)ret.findViewById(R.id.skill3)).Skill = this.hero.ActiveSkillList.get(4);
        ((ActiveSkillView)ret.findViewById(R.id.skill4)).Skill = this.hero.ActiveSkillList.get(5);

        ((PassiveSkillView)ret.findViewById(R.id.skillPassive1)).Skill = this.hero.PassiveSkillList.get(0);
        ((PassiveSkillView)ret.findViewById(R.id.skillPassive2)).Skill = this.hero.PassiveSkillList.get(1);
        ((PassiveSkillView)ret.findViewById(R.id.skillPassive3)).Skill = this.hero.PassiveSkillList.get(2);
        ((PassiveSkillView)ret.findViewById(R.id.skillPassive4)).Skill = this.hero.PassiveSkillList.get(3);

        return ret;
    }

    private Hero hero;
    public void SetHero(Hero hero){

        this.hero = hero;

    }
}
