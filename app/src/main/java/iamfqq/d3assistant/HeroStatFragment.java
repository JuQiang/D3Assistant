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

        Stat stat = hero.Stat;
        DecimalFormat intFormat = new DecimalFormat();
        intFormat.applyPattern(",###");

        DecimalFormat doubleFormat = new DecimalFormat();
        doubleFormat.applyPattern(",###.00");

        ((TextView)(view.findViewById(R.id.statStrength))).setText(intFormat.format(stat.Strength));
        ((TextView)(view.findViewById(R.id.statDexterity))).setText(intFormat.format(stat.Dexterity));
        ((TextView)(view.findViewById(R.id.statIntelligence))).setText(intFormat.format(stat.Intelligence));
        ((TextView)(view.findViewById(R.id.statVitality))).setText(intFormat.format(stat.Vitality));
        ((TextView)(view.findViewById(R.id.statDamage))).setText(intFormat.format(stat.Damage));
        ((TextView)(view.findViewById(R.id.statToughness))).setText(intFormat.format(stat.Toughness));
        ((TextView)(view.findViewById(R.id.statHealing))).setText(intFormat.format(stat.Healing));

        ((TextView)(view.findViewById(R.id.statAttackSpeed))).setText(doubleFormat.format(stat.AttackSpeed));
        ((TextView)(view.findViewById(R.id.statCritChance))).setText(intFormat.format(stat.CritChance)+"%");
        ((TextView)(view.findViewById(R.id.statCritDamage))).setText(intFormat.format(stat.CritDamage)+"%");

        ((TextView)(view.findViewById(R.id.statArmor))).setText(intFormat.format(stat.Armor));
        ((TextView)(view.findViewById(R.id.statPhysicalResist))).setText(intFormat.format(stat.PhysicalResist));
        ((TextView)(view.findViewById(R.id.statColdResist))).setText(intFormat.format(stat.ColdResist));
        ((TextView)(view.findViewById(R.id.statFireResist))).setText(intFormat.format(stat.FireResist));
        ((TextView)(view.findViewById(R.id.statLightningResist))).setText(intFormat.format(stat.LightningResist));
        ((TextView)(view.findViewById(R.id.statPoisonResist))).setText(intFormat.format(stat.PoisonResist));
        ((TextView)(view.findViewById(R.id.statArcaneResist))).setText(intFormat.format(stat.ArcaneResist));
        ((TextView)(view.findViewById(R.id.statThorns))).setText(intFormat.format(stat.Thorns));

        ((TextView)(view.findViewById(R.id.statLife))).setText(intFormat.format(stat.Life));
        ((TextView)(view.findViewById(R.id.statLifeSteal))).setText(intFormat.format(stat.LifeSteal));
        ((TextView)(view.findViewById(R.id.statLifeOnHit))).setText(intFormat.format(stat.LifeOnHit));
        ((TextView)(view.findViewById(R.id.statLifePerKill))).setText(intFormat.format(stat.LifePerKill));

        ((TextView)(view.findViewById(R.id.statPrimaryResource))).setText(intFormat.format(stat.PrimaryResource));
        ((TextView)(view.findViewById(R.id.statSecondaryResource))).setText(intFormat.format(stat.SecondaryResource));

        ((TextView)(view.findViewById(R.id.statGoldFind))).setText(intFormat.format(stat.GoldFind));
        ((TextView)(view.findViewById(R.id.statMagicFind))).setText(intFormat.format(stat.MagicFind));

        ((TextView)(view.findViewById(R.id.statLegendaryPowerWeapon))).setText(stat.LegendaryPowerWeapon);
        ((TextView)(view.findViewById(R.id.statLegendaryPowerArmor))).setText(stat.LegendaryPowerArmor);
        ((TextView)(view.findViewById(R.id.statLegendaryPowerJewelry))).setText(stat.LegendaryPowerJewelry);

        return view;

    }

    private Hero hero;

    public void SetHero(Hero hero) {
        this.hero = hero;
    }
}
