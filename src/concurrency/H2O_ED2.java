package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O_ED2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	Lock lock = new ReentrantLock();
	int hCount = 0;
	int oCount = 0;
	Condition hCondition = lock.newCondition();
	Condition oCondition = lock.newCondition();
	
	public void H() {
		lock.lock();
		while(hCount >= 2) {
			try {
				hCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hCount ++;  // how many h thread are running now
		if (hCount == 2 && oCount == 1) {
			hCount = 0;
			oCount = 0;
			oCondition.signalAll();
		}
		lock.unlock();
	}
	public void O() {
		lock.lock();
		while (oCount >= 1) {
			try {
				oCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		oCount ++;
		if (hCount == 2 && oCount == 1) {
			hCount = 0;
			oCount = 0;
			hCondition.signalAll();
		}
		lock.unlock();
	}

}
