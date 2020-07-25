# 批处理自动安装脚本
import os
libs = {"aiohttp", "scrapy"}
try:
    for lib in libs:
        os.system("pip install " + lib +
                  " -i http://mirrors.aliyun.com/pypi/simple/ --trusted-host mirrors.aliyun.com")
        print("success !!!")
except:
    print("Failed SomeWhere")