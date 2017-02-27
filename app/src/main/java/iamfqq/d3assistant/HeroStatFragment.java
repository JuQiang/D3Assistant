package iamfqq.d3assistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class HeroStatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.tab_hero_stat, container, false);
return view;
        /*
        Stat stat = hero.getStat();
        DecimalFormat intFormat = new DecimalFormat();
        intFormat.applyPattern(",###");

        DecimalFormat doubleFormat = new DecimalFormat();
        doubleFormat.applyPattern(",###.00");

        ((TextView)(view.findViewById(R.id.statStrength))).setText(intFormat.format(stat.getStrength()));
        ((TextView)(view.findViewById(R.id.statDexterity))).setText(intFormat.format(stat.getDexterity()));
        ((TextView)(view.findViewById(R.id.statIntelligence))).setText(intFormat.format(stat.getIntelligence()));
        ((TextView)(view.findViewById(R.id.statVitality))).setText(intFormat.format(stat.getVitality()));
        ((TextView)(view.findViewById(R.id.statDamage))).setText(intFormat.format(stat.getDamage()));
        ((TextView)(view.findViewById(R.id.statToughness))).setText(intFormat.format(stat.getToughness()));
        ((TextView)(view.findViewById(R.id.statHealing))).setText(intFormat.format(stat.getHealing()));


        ((TextView)(view.findViewById(R.id.statAttackSpeed))).setText(doubleFormat.format(stat.getAttackSpeed()));
        ((TextView)(view.findViewById(R.id.statCritChance))).setText(intFormat.format(stat.getCritChance())+"%");
        ((TextView)(view.findViewById(R.id.statCritDamage))).setText(intFormat.format(stat.getCritDamage())+"%");

        ((TextView)(view.findViewById(R.id.statArmor))).setText(intFormat.format(stat.getArmor()));
        ((TextView)(view.findViewById(R.id.statPhysicalResist))).setText(intFormat.format(stat.getPhysicalResist()));
        ((TextView)(view.findViewById(R.id.statColdResist))).setText(intFormat.format(stat.getColdResist()));
        ((TextView)(view.findViewById(R.id.statFireResist))).setText(intFormat.format(stat.getFireResist()));
        ((TextView)(view.findViewById(R.id.statLightningResist))).setText(intFormat.format(stat.getLightningResist()));
        ((TextView)(view.findViewById(R.id.statPoisonResist))).setText(intFormat.format(stat.getPoisonResist()));
        ((TextView)(view.findViewById(R.id.statArcaneResist))).setText(intFormat.format(stat.getArcaneResist()));
        ((TextView)(view.findViewById(R.id.statThorns))).setText(intFormat.format(stat.getThorns()));

        ((TextView)(view.findViewById(R.id.statLife))).setText(intFormat.format(stat.getLife()));
        ((TextView)(view.findViewById(R.id.statLifeSteal))).setText(intFormat.format(stat.getLifeSteal()));
        ((TextView)(view.findViewById(R.id.statLifeOnHit))).setText(intFormat.format(stat.getLifeOnHit()));
        ((TextView)(view.findViewById(R.id.statLifePerKill))).setText(intFormat.format(stat.getLifePerKill()));
        return view;
        */
    }

    private Hero hero;

    public void SetHero(Hero hero) {
        this.hero = hero;
    }
}
