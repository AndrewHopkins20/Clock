package clock;


import queuemanager.PriorityQueue;
import queuemanager.SortedArrayPriorityQueue;

public class Clock {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
        
        PriorityQueue<Alarm> queue;
        
        
        queue = new SortedArrayPriorityQueue<>(8);
    }
}
