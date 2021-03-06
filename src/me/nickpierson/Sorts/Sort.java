package me.nickpierson.Sorts;

import me.nickpierson.SortSeer.Model;
import me.nickpierson.Utils.Point;

public abstract class Sort {

	Model.Speed speed;
	protected boolean isSorting = false;
	protected int swaps, comparisons;

	public void run(final Point[] values, Model.Speed speed) {
		this.speed = speed;
		isSorting = true;
		swaps = comparisons = 0;

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				sort(values);
				isSorting = false;
			}
		});
		thread.start();
	}

	public synchronized void swap(Point[] values, int index1, int index2) {
		Point temp = values[index1];
		values[index1] = values[index2];
		values[index2] = temp;
	}

	public void sleep() {
		try {
			Thread.sleep(speed.getSleepTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		isSorting = false;
	}

	public int getSwaps() {
		return swaps;
	}

	public int getComparisons() {
		return comparisons;
	}

	public boolean isSorting() {
		return isSorting;
	}

	protected abstract void sort(Point[] points);

	public abstract String getName();
}
