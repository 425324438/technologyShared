# Redis基础精讲

## 阅读代码前准备

* jdk 1.8
* 安装redis

下载、解压、编译Redis
```bash
wget http://download.redis.io/releases/redis-6.0.6.tar.gz
tar xzf redis-6.0.6.tar.gz
cd redis-6.0.6
make
```

进入到解压后的 src 目录，通过如下命令启动Redis:

```bash
cd src
./redis-server
```
启动成功
```bash

11389:C 30 Aug 16:08:07.344 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
11389:C 30 Aug 16:08:07.344 # Redis version=4.0.11, bits=64, commit=00000000, modified=0, pid=11389, just started
11389:C 30 Aug 16:08:07.344 # Warning: no config file specified, using the default config. In order to specify a config file use ./redis-server /path/to/redis.conf
11389:M 30 Aug 16:08:07.346 * Increased maximum number of open files to 10032 (it was originally set to 256).
                _._
           _.-``__ ''-._
      _.-``    `.  `_.  ''-._           Redis 4.0.11 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._
 (    '      ,       .-`  | `,    )     Running in standalone mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379
 |    `-._   `._    /     _.-'    |     PID: 11389
  `-._    `-._  `-./  _.-'    _.-'
 |`-._`-._    `-.__.-'    _.-'_.-'|
 |    `-._`-._        _.-'_.-'    |           http://redis.io
  `-._    `-._`-.__.-'_.-'    _.-'
 |`-._`-._    `-.__.-'    _.-'_.-'|
 |    `-._`-._        _.-'_.-'    |
  `-._    `-._`-.__.-'_.-'    _.-'
      `-._    `-.__.-'    _.-'
          `-._        _.-'
              `-.__.-'

11389:M 30 Aug 16:08:07.347 # Server initialized
11389:M 30 Aug 16:08:07.347 * DB loaded from disk: 0.000 seconds
11389:M 30 Aug 16:08:07.347 * Ready to accept connections
```



links：
<a href="http://redis.cn/" target="_blank">redis中文官网</a>
<br>
<a href="http://redis.cn/commands/expire.html" target="_blank">keys的淘汰策略</a>
<br>
<a href="http://redis.cn/topics/distlock.html" target="_blank">Redis分布式锁</a>
<br>

     
          