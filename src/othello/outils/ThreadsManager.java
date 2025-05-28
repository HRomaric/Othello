package othello.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private ArrayList<Thread> threads;
    private final static ThreadsManager INSTANCE = new ThreadsManager();

    private ThreadsManager() {
        threads = new ArrayList<>();
    }

    public static ThreadsManager getInstance() {
        return INSTANCE;
    }

    public void lancer(Thread thread) {
        detruireTout();
        threads.add(thread);
        thread.start();
    }

    public void detruireTout(){
        for (Thread thread : threads) {
            thread.interrupt();
        }
        threads.clear();
    }
}
