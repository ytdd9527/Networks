![](https://cdn.jsdelivr.net/gh/ybw0014/Networks-CN@master/images/logo/logo_large.png)

网络是一个Slimefun附属插件，可以创建一个简单有效的物品存储与运输系统，并可以与货运系统交互。

![](https://cdn.jsdelivr.net/gh/ybw0014/Networks-CN@master/images/wiki/setup.png)

## 下载

**自动更新**: 汉化版构建#16及以上版本包含自动更新功能，默认开启。

点击这里下载 Networks: [下载 Networks](https://builds.guizhanss.net/ybw0014/Networks-CN/master)

<p align="center">
  <a href="https://github.com/ybw0014/Networks-CN/actions/workflows/maven.yml">
    <img src="https://github.com/ybw0014/Networks-CN/actions/workflows/maven.yml/badge.svg" alt="Java CI"/>
  </a>
  <a href="https://builds.guizhanss.net/ybw0014/Networks-CN/master">
    <img src="https://builds.guizhanss.net/f/ybw0014/Networks-CN/master/badge.svg" alt="Build status"/>
  </a>
</p>

## Wiki / 文档

你可以在这里查看所有物品的功能：[点击查看](https://slimefun-addons-wiki.guizhanss.cn/networks/)

## 网格 / 带合成的网格

网格可以让你查看网络中的所有物品，你可以直接取出一个或一组物品。你也可以通过网格将物品输入至网络中。  
带合成的网格可以让你直接使用网络中的物品进行合成。

![](https://cdn.jsdelivr.net/gh/ybw0014/Networks-CN@master/images/wiki/grid.png)

![](https://cdn.jsdelivr.net/gh/ybw0014/Networks-CN@master/images/wiki/grid_crafting.png)

## 网桥

网桥用于扩展网络。只有相邻的两个网络方块才会连起来。

## 网络单元

网络单元拥有54格空间(相当于一个大箱子)，可以直接打开，也可以通过网络访问其中的物品。  
这是为了方便存储一些无法堆叠的小量物品。

## 网络读卡器 (已废弃)

**新版本中已被网络量子存储取代。**  
网络读卡器可以放入一张内存卡。    
内存卡可以存储大量的单一物品，拥有不同等级，升级后可以存储更多物品。  
可以使用网络内存清除器来将内存卡中的物品移动至网络中。

## 网络监测器

网络监测器可以访问相邻的网络量子存储或其他附属插件的存储，从中取出或放入物品。这样就可以将这些  
目前支持:

- 无尽科技 - 存储单元
- 网络 - 量子存储

## 导入与导出

网络入口有9格的空间，每个 sf tick 可以将最多9组其中的物品输入至网络中。
网络出口每个 sf tick 可以从网络中取出一组指定的物品。

都可以与货运系统交互。

## 抓取与推送

网络提供直接与机器交互的能力，可以一定程度上取代货运的输入/输出。

网络抓取器可以从机器中提取物品至网络中。
网络推送器可以将网络中的物品推送至机器中。

## 电力

网络电容可以接收来自电力网络的能量，并存储起来以供其他网络设备使用。  
可以使用网络电表来查看网络中的电力情况。

## 自动合成

网络配方编码器可以将任何原版或Slimefun配方写入蓝图。
网络自动合成机可以使用蓝图自动在网络中提取材料并合成目标物品。
预留版的网络自动合成机会合成1组目标物品以供其他网络设备立刻使用。

## 远程控制

网络远程控制器造价昂贵，但可以让你远程打开绑定的网格。

## 垃圾回收

网络清除器可以从网络中移除指定的物品。
清除的物品会立即消失，无法找回。

## 其他

- 网络绘制器可以对网络控制器使用，以查看网络中所有连接的设备。
- 网络配置器可以复制网络方块的配置并应用到其他网络方块上。
- 网络探测器可以查询网络中设备的数量。

## 感谢

感谢 Boomer, Cai 以及 Lucky 帮忙测试并完善插件。

感谢 **mct.tantrum.org** 的服主提供的测试环境。

另外，感谢 **mc.talosmp.net** 的 **GentlemanCheesy** 成为本人的首位赞助者！每个月能多喝一杯咖啡让我能有动力更好地编写插件。
