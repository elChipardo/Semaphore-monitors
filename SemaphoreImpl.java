import java.util.ArrayList;

public class SemaphoreImpl implements SemaphoreInterface {

	//ATTRIBUTS
	int permit;
	int threadBlocked; 

	public int getThreadBlocked() {
		return threadBlocked;
	}
	//CONSTRUCTEUR
	public SemaphoreImpl() {
		this.permit = 0;
		this.threadBlocked=0;
	}

	@Override
	synchronized public void up() {
		this.permit++;
		if(threadBlocked>0){
			this.threadBlocked--;
			notify();
		}
	}

	@Override
	synchronized public void down() {
		while(this.permit == 0){
			try {
				this.threadBlocked++;
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}

		this.permit--;

	}

	@Override
	synchronized public int releaseAll() {
		int blocked = this.threadBlocked;
		this.threadBlocked=0;
		this.permit += blocked;
		notifyAll();
		
		return blocked;
	}

}
