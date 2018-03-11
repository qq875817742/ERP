package com.xinboiedu.erp.invoice.bill.business.ebo;

import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.bill.business.ebi.BillEbi;
import com.xinboiedu.erp.invoice.bill.dao.dao.BillDao;
import com.xinboiedu.erp.invoice.bill.vo.BillQueryModel;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import test.JxlUtil;

import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class BillEbo implements BillEbi{

	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}
	@Override
	public List<Object[]> getBuyBill(BillQueryModel bqm) {
		return billDao.getBuyBill(bqm);
	}
	@Override
	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		
		return billDao.getBuyBillDetail(bqm);
	}
	
	
	
	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 20));
		theme.setLargeFont(new Font("宋体", Font.PLAIN, 14));
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 12));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 10));
		ChartFactory.setChartTheme(theme);
	}
	
	
	@Override
	public void writeJFreeChartToOs(ServletOutputStream os, BillQueryModel bqm) throws IOException {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		
		List<Object[]> buyBill = billDao.getBuyBill(bqm);
		for (Object[] objects : buyBill) {
			GoodsModel gm = (GoodsModel) objects[1];
			Long num = (Long) objects[0];
			localDefaultPieDataset.setValue(gm.getName(), new Double(num));
		}
		JFreeChart localJFreeChart = ChartFactory.createPieChart("采购订单",  localDefaultPieDataset, true, true, false);
	    PiePlot localPiePlot = (PiePlot)localJFreeChart.getPlot();
	    localPiePlot.setLabelFont(new Font("SansSerif", 0, 12));
	    localPiePlot.setNoDataMessage("无查询结果");
	    localPiePlot.setCircular(false);
	    localPiePlot.setLabelGap(0.02D);
	    
	    BufferedImage bi = localJFreeChart.createBufferedImage(500, 400);

		ImageIO.write(bi, "PNG", os);
	}
	
	
	
	public InputStream getWriteExcel(BillQueryModel bqm) throws Exception {
		List<Object[]> buyBill = billDao.getBuyBill(bqm);

		//设置输出流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// 写Excel
		WritableWorkbook w = Workbook.createWorkbook(bos);

		WritableSheet s = w.createSheet("总括", 0);

		// 设置单元格宽度
		JxlUtil.sColumnSize(s, 1, 8);
		JxlUtil.sColumnSize(s, 2, 8);
		JxlUtil.sColumnSize(s, 3, 25);
		JxlUtil.sColumnSize(s, 4, 25);
		JxlUtil.sColumnSize(s, 5, 25);
		// 设置单元格高度
		JxlUtil.sRowSize(s, 1, 15);
		JxlUtil.sRowSize(s, 2, 37);
		JxlUtil.sRowSize(s, 3, 6);
		JxlUtil.sRowSize(s, 4, 23);

		// 设置合并单元格
		JxlUtil.sMerge(s, 2, 2, 2, 4);
		JxlUtil.sMerge(s, 3, 2, 3, 5);

		Label lab22 = JxlUtil.cLabel(2, 2, "进货统计报表");
		JxlUtil.sLabelStyle(lab22, "黑体", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
		JxlUtil.aLabelToSheet(lab22, s);

		Label lab25 = JxlUtil.cLabel(2, 5, "不限");
		JxlUtil.sLabelStyle(lab25, "黑体", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
		JxlUtil.aLabelToSheet(lab25, s);

		Label lab32 = JxlUtil.cLabel(3, 2, "");
		JxlUtil.sLabelStyle(lab32, "黑体", 1, Colour.BLACK, Colour.GRAY_25, 1, "2022");
		JxlUtil.aLabelToSheet(lab32, s);

		Label lab42 = JxlUtil.cLabel(4, 2, "编号");
		JxlUtil.sLabelStyle(lab42, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab42, s);

		Label lab43 = JxlUtil.cLabel(4, 3, "厂商");
		JxlUtil.sLabelStyle(lab43, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab43, s);

		Label lab44 = JxlUtil.cLabel(4, 4, "商品名");
		JxlUtil.sLabelStyle(lab44, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab44, s);

		Label lab45 = JxlUtil.cLabel(4, 5, "数量");
		JxlUtil.sLabelStyle(lab45, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
		JxlUtil.aLabelToSheet(lab45, s);

		int row = 5;
		int i = 0;
		Long allNum = 0L;
		for (; i < buyBill.size(); i++) {
			Object[] objects = buyBill.get(i);
			GoodsModel gm = (GoodsModel) objects[1];
			Long num = (Long) objects[0];
			
			// 设置行高
			JxlUtil.sRowSize(s, row + i, 19);
			// 创建所有单元格

			Label lab_data_1 = JxlUtil.cLabel(row + i, 2, i + 1 + "");
			JxlUtil.sLabelStyle(lab_data_1, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0120");
			JxlUtil.aLabelToSheet(lab_data_1, s);

			Label lab_data_2 = JxlUtil.cLabel(row + i, 3, gm.getGtm().getSm().getName());
			JxlUtil.sLabelStyle(lab_data_2, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
			JxlUtil.aLabelToSheet(lab_data_2, s);

			Label lab_data_3 = JxlUtil.cLabel(row + i, 4, gm.getName());
			JxlUtil.sLabelStyle(lab_data_3, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
			JxlUtil.aLabelToSheet(lab_data_3, s);

			Label lab_data_4 = JxlUtil.cLabel(row + i, 5, num.toString());
			JxlUtil.sLabelStyle(lab_data_4, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0112");
			JxlUtil.aLabelToSheet(lab_data_4, s);
			
			allNum += num;
		}

		// 设置最后一行高度
		JxlUtil.sRowSize(s, row + i, 25);
		// 设置最后一行的合并
		JxlUtil.sMerge(s, row + i, 2, row + i, 4);

		Label lab_tail_1 = JxlUtil.cLabel(row + i, 2, "总计：");
		JxlUtil.sLabelStyle(lab_tail_1, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab_tail_1, s);

		Label lab_tail_2 = JxlUtil.cLabel(row + i, 5, allNum.toString());
		JxlUtil.sLabelStyle(lab_tail_2, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
		JxlUtil.aLabelToSheet(lab_tail_2, s);

		w.write();
		w.close();
		
		//excel表格数据都在bos中
		// 要做Struts的下载要一个输入流
		// 输出流中是护具写到一个输入中
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		return bis;
	}
	
	
	
	
	
	
	
	
	
	
}
