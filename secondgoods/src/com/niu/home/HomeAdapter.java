package com.niu.home;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.niu.allgoods.ShowAllGoodsFragment;
import com.niu.allorder.AllOrderFragment;
import com.niu.imageload.NewGoodsFragment;
import com.niu.person.ThreeButtonFragment;

public class HomeAdapter extends FragmentPagerAdapter{
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	public HomeAdapter(FragmentManager fm) {
		super(fm);
		NewGoodsFragment n = new NewGoodsFragment();
		ThreeButtonFragment t = new ThreeButtonFragment();
		ShowAllGoodsFragment s = new ShowAllGoodsFragment();
		AllOrderFragment a = new AllOrderFragment();
		fragments.add(s);
		fragments.add(a);
		fragments.add(n);
		fragments.add(t);
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}
