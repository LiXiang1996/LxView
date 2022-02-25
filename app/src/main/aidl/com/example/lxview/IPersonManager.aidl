// IPersonManager.aidl
package com.example.lxview;

// Declare any non-default types here with import statements
import com.example.lxview.Person;
interface IPersonManager {
    List<Person> getPersonList();
    //关于这个参数in 其实你不加也是可以编译通过的，这里我就先加一下 具体参数的意义 以后会说
    void addPerson(in Person person);
    /**
    * 1.Client 发起远程调用请求 也就是RPC 到Binder。同时将自己挂起，挂起的原因是要等待RPC调用结束以后返回的结果

      2.Binder 收到RPC请求以后 把参数收集一下，调用transact方法，把RPC请求转发给service端。

      3.service端 收到rpc请求以后 就去线程池里 找一个空闲的线程去走service端的 onTransact方法 ，实际上也就是真正在运行service端的 方法了，等方法运行结束 就把结果 写回到binder中。

      4.Binder 收到返回数据以后 就唤醒原来的Client 线程，返回结果。至此，一次进程间通信 的过程就结束了
    */
}
