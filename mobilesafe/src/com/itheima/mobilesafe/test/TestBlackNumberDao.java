package com.itheima.mobilesafe.test;

import java.util.Random;

import com.itheima.mobilesafe.db.dao.BlackNumberDao;

import android.test.AndroidTestCase;

public class TestBlackNumberDao extends AndroidTestCase {
	
	
	public void testAdd() {

		BlackNumberDao dao = BlackNumberDao.getInstance(getContext());
		Random random = new Random();
		for (int i = 0; i < 120; i++) {
			int mode = random.nextInt(3) + 1;
			dao.add("1345252452" + i, mode);

		}
	}
	public void testDelete() {

		BlackNumberDao dao = BlackNumberDao.getInstance(getContext());
		dao.delete("13452524521");

		}
	public void testUpddate() {

		BlackNumberDao dao = BlackNumberDao.getInstance(getContext());
		dao.update("13452524520", 1);

		}
	public void testfind() {

		BlackNumberDao dao = BlackNumberDao.getInstance(getContext());
		boolean exist=dao.find("13452524521");
		assertEquals(false, exist);

		}
	
}
