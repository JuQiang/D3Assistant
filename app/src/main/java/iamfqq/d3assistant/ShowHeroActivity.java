package iamfqq.d3assistant;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class ShowHeroActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private LinearLayout mTabBtnWeixin;
    private LinearLayout mTabBtnFrd;
    private LinearLayout mTabBtnAddress;
    private LinearLayout mTabBtnSettings;
    private Hero myHero;

    private View viewStat, viewItem, viewSkill;//需要滑动的页卡
    private ViewPager viewPager;//viewpager  
    private PagerTitleStrip pagerTitleStrip;//viewpager的标题  
    private PagerTabStrip pagerTabStrip;//一个viewpager的指示器，效果就是一个横的粗的下划线  
    private List<View> viewList;//把需要滑动的页卡添加到这个list中  
    private List<String> titleList;//viewpager的标题  
    private Button weibo_button;//button对象，一会用来进入第二个Viewpager的示例  
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hero);

        Intent intent = this.getIntent();
        myHero = (Hero) intent.getSerializableExtra("myHero");

        Toolbar toolbar =(Toolbar) findViewById(R.id.showherotoolbar);
        toolbar.setTitle(myHero.getName());

        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        InitView();

        D3API.setContext(this);
    }

    private void InitView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        //mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
        //mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
        //mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
        //mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);

        HeroItemFragment itemFragment = new HeroItemFragment();
        itemFragment.SetHero(myHero);
        HeroStatFragment statFragment = new HeroStatFragment();
        statFragment.SetHero(myHero);
        HeroSkillFragment skillFragment = new HeroSkillFragment();
        skillFragment.SetHero(myHero);

        mFragments.add(statFragment);
        mFragments.add(itemFragment);
        mFragments.add(skillFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int currentIndex;

            @Override
            public void onPageSelected(int position) {
                //resetTabBtn();
                switch (position) {
                    case 0:
                        //((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                        //        .setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        //((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
                        //        .setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        //((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
                        //        .setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        //((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
                        //        .setImageResource(R.drawable.tab_settings_pressed);
                        break;
                }

                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
/*
    private void InitTab() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        //pagerTabStrip.setTabIndicatorColor();
        pagerTabStrip.setDrawFullUnderline(false);
        //pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.azure));
        pagerTabStrip.setTextSpacing(50);

        LayoutInflater lf = getLayoutInflater().from(this);
        viewStat = lf.inflate(R.layout.tab_hero_stat, null);
        viewItem = lf.inflate(R.layout.tab_hero_item, null);
        viewSkill = lf.inflate(R.layout.tab_hero_skill, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(viewStat);
        viewList.add(viewItem);
        viewList.add(viewSkill);

        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("属性");
        titleList.add("装备");
        titleList.add("技能");

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }


            @Override
            public CharSequence getPageTitle(int position) {

                return titleList.get(position);//直接用适配器来完成标题的显示，所以从上面可以看到，我们没有使用PagerTitleStrip。当然你可以使用。

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));

                return viewList.get(position);
            }

        };

        viewPager.setAdapter(pagerAdapter);
    }
*/
}
