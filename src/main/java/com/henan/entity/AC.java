package com.henan.entity;

public class AC
{
    class A
    {
        private int i = 1;
        
        public A()
        {
            i = 2;
        }
        
        public void print()
        {
            System.out.println("The result is " + i);
        }
    }
    
    class B extends A
    {
        private int i = 3;
        
        public B()
        {
            i = 4;
        }
        
        public void print()
        {
            System.out.println("The result is " + i);
        }
    }
    
    public void PA()
    {
        A a = new B();
        a.print();
    }
    
    public static void main(String[] args)
    {
        AC a = new AC();
        a.PA();
    }
}
