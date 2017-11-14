1 设置对应机器的为桥接模式
    虚拟机设置-->硬件-->网络适配器-->桥接

2 修改/etc/sysconfig/network-scripts/ifcfg-ens33 

3 重启:reboot

4 设置dns:sh setting_dns.sh