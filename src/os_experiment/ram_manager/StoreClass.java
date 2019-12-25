package os_experiment.ram_manager;

/**
 * @author imlgw.top
 * @date 2019/4/19 13:39
 */
public class StoreClass {

    int id;
    int[] ye = new int[64];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int[] getYe() {
        return ye;
    }

    public void setYe(int[] ye) {
        this.ye = ye;
    }
}