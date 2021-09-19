package com.nestadmin;

import java.util.Arrays;
import com.nestadmin.BaseTestClass;

public class Rough {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Object[][] ob=new Object[2][2];
ob[1][0]="ajaz";
ob[1][1]="ajaz1";
System.out.println(Arrays.deepToString(ob));
System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());


//System.out.println(log4j2);
BaseTestClass log=new BaseTestClass();
BaseTestClass.log4j2.info("test");
	}

}
