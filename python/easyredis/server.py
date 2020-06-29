from socket import *
from func import *
import time

COD = 'utf-8'
HOST = 'localhost' # 主机ip
PORT = 9999 # 软件端口号
BUFSIZ = 1024
ADDR = (HOST, PORT)
SIZE = 10
tcpS = socket(AF_INET, SOCK_STREAM) # 创建socket对象
tcpS.setsockopt(SOL_SOCKET,SO_REUSEADDR,1) #加入socket配置，重用ip和端口
tcpS.bind(ADDR) # 绑定ip端口号
tcpS.listen(SIZE)  # 设置最大链接数

print("欢迎使用easyredis!")
while True:
    conn, addr = tcpS.accept() 
    print(addr, "客户端连入")
    while True:
        # data = conn.recv(BUFSIZ) # 读取已链接客户的发送的消息
        # ndata = handle(data)
        
        try:
            data = conn.recv(BUFSIZ) # 读取已链接客户的发送的消息
            ndata = handle(data)
        except Exception:
            print(addr, "客户端断开")
            break

        # encode(COD)解决需要传递字符串时，TypeError: a bytes-like object is required, not 'str'
        conn.send(ndata.encode(COD)) #发送消息给已链接客户端
    conn.close() #关闭客户端链接
tcpS.closel()