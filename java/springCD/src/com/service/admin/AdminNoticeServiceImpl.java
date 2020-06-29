package com.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.AdminNoticeDao;
import com.po.Notice;
@Service("adminNoticeService")
@Transactional
public class AdminNoticeServiceImpl implements AdminNoticeService{
	@Autowired
	private AdminNoticeDao AdminNoticeDao;
	@Override
	public String addNotice(Notice notice) {
		AdminNoticeDao.addNotice(notice);
		return "forward:/adminNotice/deleteNoticeSelect";
	}
	@Override
	public String deleteNoticeSelect(Model model) {
		model.addAttribute("allNotices", AdminNoticeDao.deleteNoticeSelect());
		return "admin/deleteNoticeSelect";
	}
	@Override
	public String selectANotice(Model model, Integer id) {
		model.addAttribute("notice", AdminNoticeDao.selectANotice(id));
		return "admin/noticeDetail";
	}
	@Override
	public String deleteNotice(Integer id) {
		AdminNoticeDao.deleteNotice(id);
		return "forward:/adminNotice/deleteNoticeSelect";
	}

}
