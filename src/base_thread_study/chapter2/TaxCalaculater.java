package base_thread_study.chapter2;

public class TaxCalaculater {
    private final  double salary;
    private final  double bonus;

    private final  TaxCalcWay way;

    public TaxCalaculater(double salary, double bonus, TaxCalcWay way) {
        this.salary = salary;
        this.bonus = bonus;
        this.way = way;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    protected   double  calcTax(){
        return way.calcTax(salary,bonus);
    }

    public  double caculator(){
        return  this.calcTax();
    }
}
