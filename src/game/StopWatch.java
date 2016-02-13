package game;

/*
 * StopWatch-luokka toimittaa pelin ajanoton virkaa. Se k‰ytt‰‰ tietokoneen sis‰ist‰ kelloa ajan ottamiseksi.
 * Se sis‰lt‰‰ attribuutit aloitusajankohta, t‰m‰n hetkinen aika, keskeytetyn ajan ajankohta, keskeytyksen loppumisen ajankohta, kulunut aika ja viimisein kierrosaika.
 */
public class StopWatch { 

    private long start;
    private long now;
    private long pauseStart;
    private long pauseStop;
    private String elapsed;
    private double lastLap;

    public StopWatch() {
        
    } 
    /*
     * elapsedTime-metodi yll‰pit‰‰ ja palauttaa kuluneen ajan kierroksen aloitusajankohtaan n‰hden.
     */
    public String elapsedTime() {
        now = System.currentTimeMillis();
        elapsed = "" + (now - start) / 1000.0;
        return elapsed;
    } 
    /*
     * lastLap-metodi palauttaa edellisen kierroksen kierrosajan.
     */
    public double lastLap(){
    	now = System.currentTimeMillis();
        lastLap = (now - start) / 1000.0;
        return lastLap;
    }
    /*
     * pauseWatch-metodi asettaa pauseStart-arvoksi sen hetkisen ajanhetken.
     */
    public void pauseWatch(){
    	pauseStart = now;
    }
    public String getPauseStart(){
    	return "" + pauseStart;
    }
    /*
     * resumeWatch-metodi asettaa pauseStop-arvoksi sen hetkisen ajanhetken ja asettaa start-arvoksi uuden arvon, joka huomioi keskeytyksen pituuden.
     */
    public void resumeWatch(){
    	pauseStop = System.currentTimeMillis();
    	start = start + (pauseStop - pauseStart);
    }
    
    /*
     * startWatch-metodi k‰ynnist‰‰ kellon.
     */
    public void startWatch(long start){
    	this.start = start;
    }
}
