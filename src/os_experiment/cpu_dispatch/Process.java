package os_experiment.cpu_dispatch;

/**
 * 进程对象
 */
class Process {
    private String processName;

    public Process nextProcess;

    private long requireTime;

    public int priority;

    private ProcesState state;

    private long alreadyTime;

    public Process() {

    }

    //进程优先级调度算法
    public Process(String processName, Process nextProcess, long requireTime, int priority, ProcesState state) {
        this.processName = processName;
        this.requireTime = requireTime;
        this.nextProcess = nextProcess;
        this.priority = priority;
        this.state = state;
    }

    //时间片轮换
    public Process(String processName, Process nextProcess, long requireTime, ProcesState state, long alreadyTime) {
        this.processName = processName;
        this.nextProcess = nextProcess;
        this.nextProcess = nextProcess;
        this.requireTime = requireTime;
        this.state = state;
        this.alreadyTime = alreadyTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "processName='" + processName + '\'' +
                "requireTime=" + requireTime +
                ", priority=" + priority +
                ", state=" + state +
                '}';
    }

    public enum ProcesState {
        READY, END;
    }

    public String getProcessName() {
        return processName;
    }

    public Process getNextProcess() {
        return nextProcess;
    }

    public long getRequireTime() {
        return requireTime;
    }

    public int getPriority() {
        return priority;
    }

    public ProcesState getState() {
        return state;
    }

    public long getAlreadyTime() {
        return alreadyTime;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setNextProcess(Process nextProcess) {
        this.nextProcess = nextProcess;
    }

    public void setRequireTime(long requireTime) {
        this.requireTime = requireTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setState(ProcesState state) {
        this.state = state;
    }

    public void setAlreadyTime(long alreadyTime) {
        this.alreadyTime = alreadyTime;
    }
}
