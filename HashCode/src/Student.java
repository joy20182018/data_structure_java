public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    Student(int grade, int cls, String firstName, String lastName){

        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    //  java自带的hashCode是根据地址来进行的映射
    // 如果没有自定义，则可能出现不想要的情况
    public int hashCode(){


        /*
        类似计算进制数
        hash = ( ( (grade * B) + cls ) * B + firstName ) *B + lastName )
         */
        int B = 31;

        int hash = 0;
        hash = hash *B +grade;
        hash = hash *B + cls;
        hash = hash *B + firstName.toLowerCase().hashCode();
        hash = hash *B + lastName.toLowerCase().hashCode();

        return hash;   // 转换为一个整型
    }

    @Override

    // 比较这两个对象是否相同
    public boolean equals(Object o){

        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Student another = (Student)o;

        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }

}
