package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailModel;
import com.xinboiedu.erp.invoice.order.vo.OrderModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailModel;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;

public class GeneratorUtil {

	// 反射 + IO
	public static void main(String[] args) throws Exception {
		new GeneratorUtil(OperDetailModel.class);
		System.out.println("映射文件未完成");
		System.out.println("Impl中查询条件未做");
		System.out.println("struts.xml未配置");
	}

	public Class clazz;
	private String b;
	private String first;
	private String l;
	private String s;
	private String pkg;
	private String dir;

	public GeneratorUtil(Class clazz) throws Exception {
		this.clazz = clazz;
		// 生成的内容
		// 1) 数据的初始化
		dataInit();
		// 2) 创建目录
		generatorDir();
		// 3) QueryModel
		generatoQueryModel();
		// 4) Model.hbm.xml
		generatorHbm();
		// 5) Dao
		generatorDao();
		// 6) Impl
		generatorImpl();
		// 7) Ebi
		generatorEbi();
		// 8) Ebo
		generatorEbo();
		// 9) Action
		generatorAction();
		// 10) applicationContext-**.xml
		generatorApplication();
	}

	private void dataInit() {
		String className = clazz.getSimpleName();// 类名
		b = className.substring(0, className.length() - 5);// 首字母大写的前几个字母
		first = b.substring(0, 1);// 第一个字母
		l = first.toLowerCase();// 第一个小写字母
		s = l + b.substring(1);// 首字母小写的前几个字母
		String rootPkg = clazz.getPackage().getName();
		pkg = rootPkg.substring(0, rootPkg.length() - 3);
		dir = pkg.replace(".", "/");

	}

	private void generatorDir() {
		// business/ebi
		File file = new File("src/" + dir + "/business/ebi");
		file.mkdirs();
		// business/ebo
		file = new File("src/" + dir + "/business/ebo");
		file.mkdirs();
		// dao/dao
		file = new File("src/" + dir + "/dao/dao");
		file.mkdirs();
		// dao/impl
		file = new File("src/" + dir + "/dao/impl");
		file.mkdirs();
		// web
		file = new File("src/" + dir + "/web");
		file.mkdirs();
	}

	private void generatoQueryModel() throws IOException {
		// 创建文件
				File f = new File("src/" + dir + "/vo/" + b + "QueryModel.java");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("package " + pkg + ".vo;");
				bw.newLine();

				bw.newLine();

				bw.write("import com.xinboiedu.erp.base.BaseQueryModel;");
				bw.newLine();

				bw.newLine();

				bw.write("public class " + b + "QueryModel extends " + b + "Model implements BaseQueryModel{");
				bw.newLine();
				bw.write("}");
				bw.newLine();

				bw.flush();
				bw.close();
	}

	private void generatorHbm() throws IOException {
		// 创建文件
		File f = new File("src/" + dir + "/vo/" + b + "Model.hbm.xml");
		// 判断文件是否存在
		if (f.exists())
			return;
		// 创建文件
		f.createNewFile();
		// 写内容
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("<?xml version=\"1.0\"?>");
		bw.newLine();
		bw.write("<!DOCTYPE hibernate-mapping PUBLIC ");
		bw.newLine();
		bw.write("	\"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
		bw.newLine();
		bw.write("	\"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">");
		bw.newLine();
		bw.write("<hibernate-mapping >");
		bw.newLine();
		bw.write("	<class name=\"" + pkg + ".vo." + b + "Model\" table=\"t_" + s + "\">");
		bw.newLine();
		bw.write("		<id name=\"id\">");
		bw.newLine();
		bw.write("			<generator class=\"native\"></generator>");
		bw.newLine();
		bw.write("		</id>");
		bw.newLine();

		// 非主键属性的映射(反射获取字段)
		Field[] fds = clazz.getDeclaredFields();
		for (Field field : fds) {

			// 必须是私有的属性 并且不是主键（id）
			if (field.getModifiers() == Modifier.PRIVATE && !field.getName().equals("id")) {
				if (field.getType().equals(String.class) || //
						field.getType().equals(Long.class) || //
						field.getType().equals(Integer.class) || //
						field.getType().equals(Double.class) //
				) {
					bw.write("		<property name=\"" + field.getName() + "\"></property>");
					bw.newLine();
				}
			}
		}

		bw.write("	</class>");
		bw.newLine();
		bw.write("</hibernate-mapping>");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	private void generatorDao() throws IOException {
		// 创建文件
				File f = new File("src/" + dir + "/dao/dao/" + b + "Dao.java");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("package " + pkg + ".dao.dao;");
				bw.newLine();
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "Model;");
				bw.newLine();
				bw.write("import com.xinboiedu.erp.base.BaseDao;");
				bw.newLine();
				bw.newLine();
				bw.write("public interface " + b + "Dao extends BaseDao<" + b + "Model>{");
				bw.newLine();
				bw.write("}");
				bw.newLine();

				bw.flush();
				bw.close();
	}

	private void generatorImpl() throws IOException {
		// 创建文件
				File f = new File("src/" + dir + "/dao/impl/" + b + "Impl.java");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("package " + pkg + ".dao.impl;");
				bw.newLine();
				bw.newLine();
				bw.write("import org.hibernate.criterion.DetachedCriteria;");
				bw.newLine();
				bw.write("import org.hibernate.criterion.Restrictions;");
				bw.newLine();
				bw.write("import " + pkg + ".dao.dao." + b + "Dao;");
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "Model;");
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "QueryModel;");
				bw.newLine();
				bw.write("import com.xinboiedu.erp.base.BaseImpl;");
				bw.newLine();
				bw.write("import com.xinboiedu.erp.base.BaseQueryModel;");
				bw.newLine();
				bw.newLine();
				bw.write("public class " + b + "Impl extends BaseImpl<" + b + "Model> implements " + b + "Dao{");
				bw.newLine();
				bw.newLine();
				bw.write("	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)");
				bw.newLine();
				bw.write("	{");
				bw.newLine();
				bw.write("		" + b + "QueryModel " + l + "qm = (" + b + "QueryModel) bqm;");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("}");
				bw.newLine();

				bw.flush();
				bw.close();

	}

	private void generatorEbi() throws IOException {
		// 创建文件
				File f = new File("src/" + dir + "/business/ebi/" + b + "Ebi.java");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("package " + pkg + ".business.ebi;");
				bw.newLine();
				bw.newLine();
				bw.write("import org.springframework.transaction.annotation.Transactional;");
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "Model;");
				bw.newLine();
				bw.write("import com.xinboiedu.erp.base.BaseEbi;");
				bw.newLine();
				bw.write("@Transactional");
				bw.newLine();
				bw.write("public interface " + b + "Ebi extends BaseEbi<" + b + "Model> {");
				bw.newLine();
				bw.write("}");
				bw.newLine();

				bw.flush();
				bw.close();

	}

	private void generatorEbo() throws IOException {
		// 创建文件
				File f = new File("src/" + dir + "/business/ebo/" + b + "Ebo.java");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("package " + pkg + ".business.ebo;");
				bw.newLine();
				bw.newLine();
				bw.write("import java.io.Serializable;");
				bw.newLine();
				bw.write("import java.util.List;");
				bw.newLine();
				bw.write("import org.springframework.transaction.annotation.Transactional;");
				bw.newLine();
				bw.write("import " + pkg + ".business.ebi." + b + "Ebi;");
				bw.newLine();
				bw.write("import " + pkg + ".dao.dao." + b + "Dao;");
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "Model;");
				bw.newLine();
				bw.write("import com.xinboiedu.erp.base.BaseQueryModel;");
				bw.newLine();
				bw.newLine();
				bw.write("@Transactional");
				bw.newLine();
				bw.write("public class " + b + "Ebo implements " + b + "Ebi{");
				bw.newLine();
				bw.newLine();
				bw.write("	private " + b + "Dao " + s + "Dao;");
				bw.newLine();
				bw.write("	public void set" + b + "Dao(" + b + "Dao " + s + "Dao) {");
				bw.newLine();
				bw.write("		this." + s + "Dao = " + s + "Dao;");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public void save(" + b + "Model " + l + "m) {");
				bw.newLine();
				bw.write("		" + s + "Dao.save(" + l + "m);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public List<" + b + "Model> getAll() {");
				bw.newLine();
				bw.write("		return " + s + "Dao.getAll();");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public " + b + "Model get(Serializable id) {");
				bw.newLine();
				bw.write("		return " + s + "Dao.get(id);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public void update(" + b + "Model " + l + "m) {");
				bw.newLine();
				bw.write("		" + s + "Dao.update(" + l + "m);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public void delete(" + b + "Model " + l + "m) {");
				bw.newLine();
				bw.write("		" + s + "Dao.delete(" + l + "m);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public List<" + b + "Model> getAll(BaseQueryModel dqm) {");
				bw.newLine();
				bw.write("		return " + s + "Dao.getAll(dqm);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public List<" + b + "Model> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {");
				bw.newLine();
				bw.write("		return " + s + "Dao.getAll(dqm,pageNum,pageCount);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	@Override");
				bw.newLine();
				bw.write("	public Integer getCount(BaseQueryModel dqm) {");
				bw.newLine();
				bw.write("		return " + s + "Dao.getCount(dqm);");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("}");
				bw.newLine();

				bw.flush();
				bw.close();

	}

	private void generatorAction() throws IOException {
		// 创建文件
				File f = new File("src/" + dir + "/web/" + b + "Action.java");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("package " + pkg + ".web;");
				bw.newLine();
				bw.newLine();
				bw.write("import java.util.List;");
				bw.newLine();
				bw.write("import " + pkg + ".business.ebi." + b + "Ebi;");
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "Model;");
				bw.newLine();
				bw.write("import " + pkg + ".vo." + b + "QueryModel;");
				bw.newLine();
				bw.write("import com.xinboiedu.erp.base.BaseAction;");
				bw.newLine();
				bw.newLine();
				bw.write("public class " + b + "Action extends BaseAction {");
				bw.newLine();
				bw.newLine();
				bw.write("	private " + b + "Ebi " + s + "Ebi;");
				bw.newLine();
				bw.write("	public void set" + b + "Ebi(" + b + "Ebi " + s + "Ebi) {");
				bw.newLine();
				bw.write("		this." + s + "Ebi = " + s + "Ebi;");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	public " + b + "Model " + l + "m = new " + b + "Model();");
				bw.newLine();
				bw.write("	public " + b + "QueryModel " + l + "qm = new " + b + "QueryModel();");
				bw.newLine();
				bw.newLine();
				bw.write("	public String list() {");
				bw.newLine();
				bw.write("		setCount(" + s + "Ebi.getCount(" + l + "qm));");
				bw.newLine();
				bw.write("		List<" + b + "Model> " + s + "List = " + s + "Ebi.getAll(" + l + "qm, pageNum, pageCount);");
				bw.newLine();
				bw.write("		put(\"" + s + "List\", " + s + "List);");
				bw.newLine();
				bw.write("		return \"list\";");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	public String input() {");
				bw.newLine();
				bw.write("		if (" + l + "m.getId() != null) { ");
				bw.newLine();
				bw.write("			" + l + "m = " + s + "Ebi.get(" + l + "m.getId());");
				bw.newLine();
				bw.write("		}");
				bw.newLine();
				bw.write("		return \"input\";");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	public String save() {");
				bw.newLine();
				bw.write("		if (" + l + "m.getId() == null) {");
				bw.newLine();
				bw.write("			" + s + "Ebi.save(" + l + "m);");
				bw.newLine();
				bw.write("			setSavePage(" + s + "Ebi.getCount(" + l + "qm));");
				bw.newLine();
				bw.write("		} else {");
				bw.newLine();
				bw.write("			" + s + "Ebi.update(" + l + "m);");
				bw.newLine();
				bw.write("		}");
				bw.newLine();
				bw.write("		return \"toList\";");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("	public String delete() {");
				bw.newLine();
				bw.write("		" + s + "Ebi.delete(" + l + "m);");
				bw.newLine();
				bw.write("		return \"toList\";");
				bw.newLine();
				bw.write("	}");
				bw.newLine();
				bw.write("}");
				bw.newLine();

				bw.flush();
				bw.close();
	}

	private void generatorApplication() throws IOException {
		// 创建文件
				File f = new File("config/applicationContext-"+s+".xml");
				// 判断文件是否存在
				if (f.exists())
					return;
				// 创建文件
				f.createNewFile();
				// 写内容
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));

				bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				bw.newLine();
				bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
				bw.newLine();
				bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
				bw.newLine();
				bw.write("	xmlns:context=\"http://www.springframework.org/schema/context\"");
				bw.newLine();
				bw.write("	xsi:schemaLocation=\"http://www.springframework.org/schema/beans");
				bw.newLine();
				bw.write("    	 http://www.springframework.org/schema/beans/spring-beans.xsd");
				bw.newLine();
				bw.write("     	 http://www.springframework.org/schema/context");
				bw.newLine();
				bw.write("         http://www.springframework.org/schema/context/spring-context.xsd\">");
				bw.newLine();
				bw.newLine();
				bw.write("	<bean id=\""+s+"Dao\" class=\""+pkg+".dao.impl."+b+"Impl\"");
				bw.newLine();
				bw.write("		parent=\"baseDao\"");
				bw.newLine();
				bw.write("	></bean>");
				bw.newLine();
				bw.newLine();
				bw.write("	<bean id=\""+s+"Ebi\" class=\""+pkg+".business.ebo."+b+"Ebo\">");
				bw.newLine();
				bw.write("		<property name=\""+s+"Dao\" ref=\""+s+"Dao\"></property>");
				bw.newLine();
				bw.write("	</bean>");
				bw.newLine();
				bw.newLine();
				bw.write("	<bean id=\""+s+"Action\" class=\""+pkg+".web."+b+"Action\"");
				bw.newLine();
				bw.write("		scope=\"prototype\"");
				bw.newLine();
				bw.write("	>");
				bw.newLine();
				bw.write("		<property name=\""+s+"Ebi\" ref=\""+s+"Ebi\"></property>");
				bw.newLine();
				bw.write("	</bean>");
				bw.newLine();
				bw.write("</beans> ");
				bw.newLine();

				bw.flush();
				bw.close();
	}

}
