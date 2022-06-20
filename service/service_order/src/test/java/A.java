public class A {
    A a;

    void test() {
        System.out.println("aaaa");
    }

    public static void main(String[] args) {
        A a = new A();
        a.a = new B();
        a.a.test();
    }
}

class B extends A {
    void test() {
        System.out.println("bbbb");
    }
}