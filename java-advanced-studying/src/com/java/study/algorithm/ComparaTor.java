package com.java.study.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class CarComp implements Comparator<Car> {

	@Override
	public int compare(Car arg0, Car arg1) {
		return 0;
	}
}

public class ComparaTor {

	public static void main(String[] args) {
		List<Car> clist = new ArrayList<Car>();
		clist.add(new Car(1800));
		clist.add(new Car(1200));
		
		List<Ecar> elist = new ArrayList<Ecar>();
		elist.add(new Ecar(3000));
		elist.add(new Ecar(2500));
		
		CarComp comp = new CarComp();
		
		// public static <T> void sotr(List<T> list, Comparator<T> d)
		// public static <T> void sotr(List<T> list, Comparator<? super T> d) 
		// - 하한 제한 
		Collections.sort(clist, comp);
		Collections.sort(elist, comp);
		// ........
		
	}

}
