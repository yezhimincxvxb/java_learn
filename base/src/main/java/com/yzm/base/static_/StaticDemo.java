package com.yzm.base.static_;

public class StaticDemo {

    /**
     * 1、被static修饰的变量或者方法是独立于该类的任何对象，也就是说，这些变量和方法不属于任何一个实例对象，而是被类的实例对象所共享。
     *
     * 2、在该类被第一次加载的时候，就会去加载被static修饰的部分，而且只在类第一次使用时加载并进行初始化，注意这是第一次用就要初始化，
     *    后面根据需要是可以再次赋值的。
     *
     * 3、static变量值在类加载的时候分配空间，以后创建类对象的时候不会重新分配。赋值的话，是可以任意赋值的！
     *
     * 4、被static修饰的变量或者方法是优先于对象存在的，也就是说当一个类加载完毕之后，即便没有创建对象，也可以去访问。
     */

    /**
     * static应用场景
     * 因为static是被类的实例对象所共享，因此如果某个成员变量是被所有对象所共享的，那么这个成员变量就应该定义为静态变量。
     * 常见的应用场景有：
     * 1、修饰成员变量 2、修饰成员方法 3、静态代码块 4、修饰类【只能修饰内部类也就是静态内部类】 5、静态导包
     *
     * static注意事项
     * 1、静态只能访问静态。 2、非静态既可以访问非静态的，也可以访问静态的。
     */
    public static void main(String[] args) {

    }

}