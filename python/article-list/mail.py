from email import encoders
from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr

import smtplib

subscribe_tpl = '''
<!DOCTYPE html>
<html>
<body>
<h2>用户订阅激活</h2>
<p>
请点击下列激活链接完成订阅：
<a href="{0}" title="">{0}</a>
链接有效时间：2小时
</p>
</body>
</html>
'''

post_tpl = '''
<!DOCTYPE html>
<html>
<body>
<h2>新文章订阅</h2>
<p>
您订阅的博文有新文章发布，请点击链接查看：
<a href="{0}" title="">{0}</a>
感谢您的订阅
</p>
</body>
</html>
'''

tpl = {
	'subscribe':subscribe_tpl,
	'post':post_tpl
}

def _format_addr(s):
	name, addr = parseaddr(s)
	return formataddr((Header(name, 'utf-8').encode(), addr))

def send_email(to_addr, url, tpl_type):
	from_addr = 'notify@siseunix.net'
	password = 'notify123456'
	smtp_server = 'smtp.siseunix.net'

	content = tpl[tpl_type]
	subject = '激活邮件' if tpl_type == 'subscribe' else '订阅邮件'

	msg = MIMEText(content.format(url), 'html', 'utf-8')
	msg['From'] = _format_addr('订阅邮件 <%s>' % from_addr)
	msg['To'] = _format_addr('订阅用户 <%s>' % to_addr)
	msg['Subject'] = Header(subject, 'utf-8').encode()

	server = smtplib.SMTP(smtp_server, 587)
	server.set_debuglevel(1)
	server.ehlo()
	server.starttls()
	server.login(from_addr, password)
	server.sendmail(from_addr, [to_addr], msg.as_string())
	server.quit()
	server.close()
