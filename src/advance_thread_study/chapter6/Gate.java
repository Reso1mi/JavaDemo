package advance_thread_study.chapter6;

/**
 * ShareResource
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 临界值
     *
     * @param name
     * @param address
     */
    public void pass(String name, String address) {
        synchronized(this){
            this.counter++;
            this.address = address;
            this.name = name;
            verify();
        }
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("******BROKEN***** " + this.toString());
        }
    }

    @Override
    public String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
