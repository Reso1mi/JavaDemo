package juc_study.atomic;

/**
 * @author imlgw.top
 * @date 2019/4/22 16:54
 */
public class GetLockException extends Exception{
    public GetLockException(String message) {
        super(message);
    }

    public GetLockException() {
        super();
    }
}
