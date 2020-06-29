from socket import *
from time import ctime


HOST = 'localhost' #服务端ip
PORT = 9999 #服务端端口号
BUFSIZ = 1024
ADDR = (HOST, PORT)
tcpCliSock = socket(AF_INET, SOCK_STREAM) #创建socket对象
tcpCliSock.connect(ADDR) #连接服务器

while True:
    try:
        data = input('>>').strip()
        if not data:
            continue
        if data == 'exit':
            break
        tcpCliSock.send(data.encode('utf-8')) #发送消息

        data = tcpCliSock.recv(BUFSIZ) #读取消息
        if not data:
            print('您输入的命令有误')
            break
        print(data.decode('utf-8'))
    except KeyboardInterrupt as e:
        print('')
        print('欢迎下次使用！')
        break

tcpCliSock.close() #关闭客户端