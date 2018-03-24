
public interface SemaphoreInterface {
	public void up();
	public void down();
	public int releaseAll();
	public int getThreadBlocked();
}
