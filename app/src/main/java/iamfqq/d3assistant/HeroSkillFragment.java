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
        View ret = inflater.inflate(R.layout.tab_hero_skill, container, false);

        String uriPrefix = "http://d3.blizzard.cn/action/profile/career/"+this.hero.BattleTag+"/"+String.valueOf(hero.ID)+"/";
        ((SkillView)ret.findViewById(R.id.skillL)).SetSkill(uriPrefix,this.hero.ActiveSkillList.get(0));
        ((SkillView)ret.findViewById(R.id.skillR)).SetSkill(uriPrefix, this.hero.ActiveSkillList.get(1));
        ((SkillView)ret.findViewById(R.id.skill1)).SetSkill(uriPrefix, this.hero.ActiveSkillList.get(2));
        ((SkillView)ret.findViewById(R.id.skill2)).SetSkill(uriPrefix,this.hero.ActiveSkillList.get(3));
        ((SkillView)ret.findViewById(R.id.skill3)).SetSkill(uriPrefix,this.hero.ActiveSkillList.get(4));
        ((SkillView)ret.findViewById(R.id.skill4)).SetSkill(uriPrefix, this.hero.ActiveSkillList.get(5));

        ((SkillView)ret.findViewById(R.id.skillPassive1)).SetSkill(uriPrefix,this.hero.PassiveSkillList.get(0));
        ((SkillView)ret.findViewById(R.id.skillPassive2)).SetSkill(uriPrefix,this.hero.PassiveSkillList.get(1));
        ((SkillView)ret.findViewById(R.id.skillPassive3)).SetSkill(uriPrefix,this.hero.PassiveSkillList.get(2));
        ((SkillView)ret.findViewById(R.id.skillPassive4)).SetSkill(uriPrefix,this.hero.PassiveSkillList.get(3));

        return ret;
    }

    private Hero hero;
    public void SetHero(Hero hero){

        this.hero = hero;

    }
}
