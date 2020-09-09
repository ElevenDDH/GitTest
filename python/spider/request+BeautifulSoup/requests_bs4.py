# 爬取下厨房首页图片

from bs4 import BeautifulSoup
from urllib.parse import urlparse
import requests
import os

s = requests.Session()
s.headers['User-Agent'] = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu ' \
                          'Chromium/34.0.1847.116 Chrome/34.0.1847.116 Safari/537.36 '
s.headers['Host'] = 'www.xiachufang.com'
r = s.get('http://www.xiachufang.com/')

# 创建BeautifulSoup对象
soup = BeautifulSoup(r.text, features="lxml")

# 解析出img链接地址
img_list = []
for img in soup.select('img'):
    if img.has_attr('data-src'):
        img_list.append(img.attrs['data-src'])
    else:
        img_list.append(img.attrs['src'])

# 初始化下载文件目录
image_dir = os.path.join(os.curdir, 'images')
if not os.path.isdir(image_dir):
    os.mkdir(image_dir)

# 请求图片
for img in img_list:
    # 去path斜杠去部分@    /xxx@xx
    o = urlparse(img)
    filename = o.path[1:].split('@')[0]
    file_path = os.path.join(image_dir, filename)
    # 解决可能会有子目录SVG问题
    if not os.path.isdir(os.path.dirname(file_path)):
        os.mkdir(os.path.dirname(file_path))

    # http:/..2046h.jpg?imageView2/1/w/235/h/138/interlace/1/q/90
    # 去?后面的改变图片样式参数
    url = '%s://%s/%s' % (o.scheme, o.netloc, filename)

    resp = requests.get(url)
    with open(file_path, 'wb') as f:
        # iter_content边下载边存硬盘, 1024可以自由调整为可以更好地适合您的用例的数字
        for item in resp.iter_content(1024):
            f.write(item)
