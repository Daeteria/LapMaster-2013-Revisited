package game;

public class Drawhandler implements Runnable {
	GameSession session;
	boolean pause;
	private boolean hasPainted;
	private boolean hasStarted = false;
	private boolean hasWaited;
	private int a;
	private int b;
	private int c;
	public synchronized void setPause(boolean p){
		pause = p;
	}
	public Drawhandler(GameSession session){
		this.session = session;
	}
	public void run() {
		
		while(true){
			if(!hasWaited){
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				break;
			}
			}
				if(session.physicsCalculated){
					hasWaited = false;
				if(pause){
					if(!hasPainted){
						session.repaint();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						hasPainted = true;
					}
				}
				else if(!pause){
					session.repaint();
					hasPainted = false;
				}
			
           }
				else{
					hasWaited = true;
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				break;
			}
				}
		}
		
		
		
	}
	
	

}
