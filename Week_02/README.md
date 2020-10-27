[TOC]

# 学习笔记

## 作业1

###  串行/并行/CMS/G1 垃圾收集器对比

#### 相同4g的Xmx,Xms参数设置下

![4g汇总](./src/main/resources/4g.png)

#### 相同2g的Xmx,Xms参数设置下

![2g汇总](./src/main/resources/2g.png)

#### 相同1g的Xmx,Xms参数设置下
![1g汇总](./src/main/resources/1g.png)



##### 从以上图中可知：

1. 新生代足够大就不会触发 full gc，如果触发 full gc，相对堆空间越大时间越久
2. 每个垃圾收集器在没有设置 Xmn 都会按照各自比例设置年轻代、老年代的值
3. 堆空间越小，那么年轻代空间会小导致有大对象直接进入老年代，会频繁触发 full gc 
4. Serial 垃圾收集器比 ParNew/Parallel Scavenge 垃圾收集器回收时间长，因为 Serial 是单线程， ParNew/Parallel Scavenge 是多线。
   Parallel Old 垃圾收集器比 CMS 垃圾收集器回收时间长，因为 CMS 除了初始标记/重新标记的其他阶段业务线程可以与垃圾回收线程一起执行
5. CMS 比 G1 垃圾收集器回收时间长，G1 垃圾收集器采用全新的概念，是部分 + Region 垃圾收集器的代表。
    不回收所有区域，根据 GCMaxPauseMillis(默认200ms) 来控制
6. Serial 适合使用在小型客户端上，因为内存小停顿时间短。ParNew + CMS 适合多核低延迟，因为 CMS 是并发收集器。
   Parallel Scavenge + Parallel Old 适合高吞吐量，也是JDK 8 默认的垃圾收集器。
   G1 是部分垃圾收集器，可以做到比 CMS 还要低延迟! 暂停时间可以设置在合理的 50-100ms 内。



##  NIO 概念
1. Buffer: 在 NIO 库里面，所有数据都是用缓冲区处理(在面向流 I/O 中，可以将数据直接写入或者将数据直接读到 Stream 对象中)
2. Channel：网络数据通过 Channel 读入和写入。通道与流的不同之处在于通道是双向的，流只是一个方向上移动，而通道可以用于读、写或者二者同时进行
